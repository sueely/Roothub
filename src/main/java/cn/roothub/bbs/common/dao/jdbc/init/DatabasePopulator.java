package cn.roothub.bbs.common.dao.jdbc.init;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.roothub.bbs.common.dao.jdbc.exceptions.ScriptException;
import cn.roothub.bbs.common.dao.jdbc.util.ScriptUtils;
import cn.roothub.bbs.common.util.Assert;
import cn.roothub.bbs.core.io.Resource;

/**
 * 使用外部资源中定义的 SQL 脚本填充，初始化或清理数据库。
 * @author: miansen.wang
 * @date: 2019-12-07
 */
public class DatabasePopulator {

	/**
	 * DDL 或者 DML 脚本资源
	 */
	private List<Resource> scripts = new ArrayList<>();

	/**
	 * SQL 脚本语句分隔符，默认为 ";"。如果未指定，则以 "\n" 作为最后手段。
	 */
	private String separator = ScriptUtils.DEFAULT_STATEMENT_SEPARATOR;

	/**
	 * 执行 SQL 脚本发生错误时是否继续而不引发异常
	 */
	private boolean continueOnError = false;

	/**
	 * 执行 DROP 语句发生错误时是否继续而不引发异常
	 */
	private boolean ignoreFailedDrops = false;

	/**
	 * 使用默认配置创建新的实例
	 */
	public DatabasePopulator() {

	}

	/**
	 * 使用指定的 SQL 脚本资源创建新的实例
	 * @param scripts 要执行的初始化或清理数据库的脚本（不允许为 null）
	 */
	public DatabasePopulator(Resource... scripts) {
		setScripts(scripts);
	}

	/**
	 * 使用指定的值创建新的实例
	 * @param scripts 要执行的初始化或清理数据库的脚本（不允许为 null）
	 * @param separator SQL 脚本语句分隔符，默认为 ";"
	 * @param continueOnError 执行 SQL 脚本发生错误时是否继续而不引发异常
	 * @param ignoreFailedDrops 执行 DROP 语句发生错误时是否继续而不引发异常
	 */
	public DatabasePopulator(List<Resource> scripts, String separator, boolean continueOnError,
			boolean ignoreFailedDrops) {
		this.scripts = scripts;
		this.separator = separator;
		this.continueOnError = continueOnError;
		this.ignoreFailedDrops = ignoreFailedDrops;
	}

	/**
	 * 使用提供的 JDBC 连接填充、初始化或清理数据库。
	 * <p>如果遇到错误，可能会抛出 ScriptException。
	 * @param connection 用于填充、初始化或清理数据库的 JDBC 连接。请确保已配置并可用，不允许为 null。
	 * @throws ScriptException 与 SQL 脚本处理相关的异常
	 */
	public void populate(Connection connection) throws ScriptException {
		Assert.notNull(connection, "Connection must not be null");
	}

	/**
	 * 设置要执行的脚本资源以初始化或清理数据库，替换之前添加的所有脚本。
	 * @param scripts SQL 脚本资源
	 */
	public void setScripts(Resource... scripts) {
		this.scripts = new ArrayList<>(Arrays.asList(scripts));
	}

	/**
	 * 设置 SQL 脚本的语句分隔符，默认为 ";"。
	 * @param separator 脚本语句分隔符
	 */
	public void setSeparator(String separator) {
		this.separator = separator;
	}

	/**
	 * 执行 SQL 失败时是否引发异常的标志。默认为false。
	 * @param continueOnError {@code true} 忽略失败的 SQL 语句，继续往下执行。
	 */
	public void setContinueOnError(boolean continueOnError) {
		this.continueOnError = continueOnError;
	}

	/**
	 * 执行 DROP 语句失败时是否引发异常的标志。默认为false。
	 * @param ignoreFailedDrops {@code true} 忽略失败的 DROP 语句，继续往下执行。
	 */
	public void setIgnoreFailedDrops(boolean ignoreFailedDrops) {
		this.ignoreFailedDrops = ignoreFailedDrops;
	}

}
