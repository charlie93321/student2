package com.spring.jdbc;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR: 小于
 * @DATE: [2019/3/7  14:09]
 * @DESC:
 */
@Component
@Data
public class C3p0DataSource {
    @Value("${c3p0.driver}")
    private String driver;
    @Value("${c3p0.jdbcUrl}")
    private String jdbcUrl;
    @Value("${c3p0.userName}")
    private String userName;
    @Value("${c3p0.password}")
    private String password;


    /**当连接池中的连接耗尽的时候c3p0一次同时获取的连接数*/
    @Value("${c3p0.acquireIncrement}")
    private Integer acquireIncrement;
    /**连接池中保留的最小连接数，默认为：3*/
    @Value("${c3p0.minPoolSize}")
    private Integer minPoolSize;
    /**接池中保留的最大连接数。默认值: 15*/
    @Value("${c3p0.maxPoolSize}")
    private Integer maxPoolSize;
    /**初始化时获取连接数，取值应在minPoolSize与maxPoolSize之间。Default: 3*/
    @Value("${c3p0.initialPoolSize}")
    private Integer initialPoolSize;
    /**最大空闲时间，多少秒内未使用则连接被丢弃。若为0则永不丢弃。默认值: 0*/
    @Value("${c3p0.maxIdleTime}")
    private Integer maxIdleTime;

    /**c3p0全局的PreparedStatements缓存的大小。如果maxStatements与maxStatementsPerConnection均为0，则缓存不生效，只要有一个不为0，则语句的缓存就能生效。如果默认值: 0
     */
    @Value("${c3p0.maxStatements}")
     private Integer maxStatements;


    /**
     * c3p0是异步操作的，缓慢的JDBC操作通过帮助进程完成。扩展这些操作可以有效的提升性能通过多线程实现多个操作同时被执行。Default: 3
     *
     */
    @Value("${c3p0.numHelperThreads}")
     private Integer numHelperThreads;

     /**定义在从数据库获取新连接失败后重复尝试的次数。默认值: 30 ；小于等于0表示无限次*/
     @Value("${c3p0.acquireRetryAttempts}")
     private Integer acquireRetryAttempts;
    /**重新尝试的时间间隔，默认为：1000毫秒*/
    @Value("${c3p0.acquireRetryDelay}")
     private Integer acquireRetryDelay;

     /**获取一个connection超时时间，单位毫秒*/
     @Value("${c3p0.checkoutTimeout}")
     private Integer checkoutTimeout;
/**每隔多少秒检查所有连接池中的空闲连接。Default: 0*/
@Value("${c3p0.idleConnectionTestPeriod}")
    private Integer idleConnectionTestPeriod;

    /**
     *  c3p0将建一张名为改配置项的空表，并使用其自带的查询语句进行测试。
     *  如果定义了这个参数那么属性preferredTestQuery将#被忽略。你不能在这张Test表上进行任何操作，
     *  它将只供c3p0测试使用。默认值: null。
     *  由于运营平台的数据库用户没有创建表的权限，故需要发sql创建表
     */
    @Value("${c3p0.automaticTestTable}")
    private String automaticTestTable;

    /**
     * 如果设为true那么在取得连接的同时将校验连接的有效性。Default: false
     */
    @Value("${c3p0.testConnectionOnCheckin}")
    private boolean testConnectionOnCheckin;

    /**
     * 一个checkout连接的超时设置，一旦一个checkout连接超时，
     * 他将物理的关闭，而不是返回池中，主要是防止连接被长期使用不释放，这个设置也是比较危险的
     * 设置连接被checkout后，经过多长时间还未返还连接池，则连接缓冲池直接Kill该连接，
     * 此时，结合 debugUnretrunedConnectionStackTraces，
     * 我们可以知道是什么程序持有了连接，并且没有返回。这个方法很暴力，有可能 造成应用程序不稳定，
     * 所以，如果真的出现了连接泄漏，一时半会又找不到问题点所在，可以暂时使用这个方法让应用程序跑起来。
     */
    @Value("${c3p0.unreturnedConnectionTimeout}")
    private Integer unreturnedConnectionTimeout;


    /**
     * 默认为false，如果此参数设置为true，当所有连接用完的时候，会以堆栈信息显示哪些代码使用了连接。由于跟踪连接状态会产生额外的消耗，如果程序稳定，应该将此参数恢复为默认值false
     *
     */
    @Value("${c3p0.debugUnreturnedConnectionStackTraces}")
    private boolean debugUnreturnedConnectionStackTraces;
}
