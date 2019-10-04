package com.boot.config;

import com.boot.listener.JobListener;
import com.boot.pojo.activiti.User;
import com.boot.rowmap.UserRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.support.ListPreparedStatementSetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by L on 2019/10/4.
 * @EnableBatchProcessing提供用于构建批处理作业的基本配置
 * 配置一个最基本的Job : 一个Job 通常由一个或多个Step组成(基本就像是一个工作流)；
 * 一个Step通常由三部分组成(读入数据 ItemReader，处理数据 ItemProcessor，写入数据 ItemWriter)
 */
@Configuration
@EnableBatchProcessing
public class DataBatchConfiguration {
    private static final Logger log = LoggerFactory.getLogger(DataBatchConfiguration.class);

    @Resource
    private JobBuilderFactory jobBuilderFactory;    //用于构建JOB

    @Resource
    private StepBuilderFactory stepBuilderFactory;  //用于构建Step

    @Resource
    private EntityManagerFactory emf;           //注入实例化Factory 访问数据

    @Resource
    private JobListener jobListener;            //简单的JOB listener

    @Autowired
    @Qualifier("activitiDataSource")
    private DataSource activitiDataSource;
    /**
     * 一个简单基础的Job通常由一个或者多个Step组成
     */
    @Bean
    public Job dataHandleJob() {
        return jobBuilderFactory.get("dataHandleJob").
                incrementer(new RunIdIncrementer()).
                start(handleDataStep()).    //start是JOB执行的第一个step
//                next(xxxStep()).
//                next(xxxStep()).
//                ...
        listener(jobListener).      //设置了一个简单JobListener
                build();
    }

    /**
     * 一个简单基础的Step主要分为三个部分
     * ItemReader : 用于读取数据
     * ItemProcessor : 用于处理数据
     * ItemWriter : 用于写数据
     */
    @Bean
    public Step handleDataStep() {
        return stepBuilderFactory.get("getData").
                <User, User>chunk(100).        // <输入,输出> 。chunk通俗的讲类似于SQL的commit; 这里表示处理(processor)100条后写入(writer)一次。
                faultTolerant().retryLimit(3).retry(Exception.class).skipLimit(100).skip(Exception.class). //捕捉到异常就重试,重试100次还是异常,JOB就停止并标志失败
                reader(getDataReader()).         //指定ItemReader
                processor(getDataProcessor()).   //指定ItemProcessor
                writer(getDataWriter()).         //指定ItemWriter
                build();
    }

    @Bean
    public ItemReader<User> getDataReader() {
        //读取数据,这里可以用JPA,JDBC,JMS 等方式 读入数据
        JdbcCursorItemReader<User> reader = new JdbcCursorItemReader<>();
        try {
            reader.setDataSource(activitiDataSource);
            List parameters = new LinkedList<>();
//            parameters.add("id");
//            parameters.add("login_name");
            reader.setPreparedStatementSetter(new ArgumentPreparedStatementSetter(parameters.toArray()));
            reader.setSql("select id, login_name from user");
            reader.setRowMapper(new UserRowMapper());
            reader.afterPropertiesSet();
            //所有ItemReader和ItemWriter实现都会在ExecutionContext提交之前将其当前状态存储在其中,如果不希望这样做,可以设置setSaveState(false)
            reader.setSaveState(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reader;
    }

    @Bean
    public ItemProcessor<User, User> getDataProcessor() {
//        return new ItemProcessor<User, User>() {
//            @Override
//            public User process(User User) throws Exception {
//                log.info("processor data : " + User.toString());  //模拟  假装处理数据,这里处理就是打印一下
//                return User;
//            }
//        };
//        lambda也可以写为:
        return User -> {
            log.info("processor data : " + User.toString());
            return User;
        };
    }

    @Bean
    public ItemWriter<User> getDataWriter() {
        return list -> {
            for (User User : list) {
                log.info("write data : " + User); //模拟 假装写数据 ,这里写真正写入数据的逻辑
            }
        };
    }
}