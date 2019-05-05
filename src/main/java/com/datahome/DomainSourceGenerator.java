package com.datahome;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

/**
	author:taft
*/
public class DomainSourceGenerator {
	private String domain;
	private String domainID;
	private String home;
	private String basePackageName;
	private String domainFirstLower;
	private String domainService;
	private String domainServiceImpl;
	private String domainDao;
	private String domainDaoImpl;
	private String domainGroup;

	private String domainRepository;

	private static final String DAOIMPL = "DaoImpl";
	private static final String DAO = "Dao";
	private static final String SERVICE = "Service";
	private static final String SERVICEIMPL = "ServiceImpl";
	private static final String REPOSITORY = "Repository";
	private static final String GROUP = "Group";
	
	// 构造方法，加载属性文件，查找路径
	public DomainSourceGenerator() {
		Properties prop = new Properties();
		try {
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("sourceCodeGeneratorConfig.properties");
			prop.load(inStream);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		domain = "GdnDataBatch";
		domainID = "Integer";
		home = prop.getProperty("home");
		basePackageName = prop.getProperty("basePackageName");
		domainFirstLower = this.getFirstLower(domain);
		domainService = domain+"Service";
		domainServiceImpl = domain+"ServiceImpl";
		domainRepository = domain + "Repository";

		domainDao = domain+"Dao";
		domainDaoImpl = domain + "DaoImpl";

		domainGroup=domain+"Group";
	}
	
	/**
	 * 生成所有的java文件（service接口、service的实现类、respository接口）
	 * @throws Exception
	 */
	@Test
	public void generateAll() throws Exception {


		this.generateRespository();
		this.generateService();
		this.generateServiceImpl();
		this.generateDao();
		this.generateDaoimpl();
		this.generategroup();
	}

	private void generategroup() throws IOException {
		PrintWriter writer = this.getWriter(GROUP);
		writer.println("public interface " + domainGroup + " {");
		writer.println("");
		writer.println("");
		writer.println("}");
		writer.close();
	}

	private void generateDao() throws IOException {
		PrintWriter writer = this.getWriter(DAO);
		writer.println("public interface " + domainDao + " {");
		writer.println("");
		writer.println("");
		writer.println("}");
		writer.close();
	}

	/**
	 * 生成dao和dao impl文件
	 * @throws Exception
	 */
	@Test
	public void generateRespositoryAnddaoAnddaoimp() throws Exception {
		//this.generateRespository();
		this.generateDao();
		this.generateDaoimpl();
	}

	/**
	 * 生成service和service impl文件
	 * @throws Exception
	 */
	@Test
	public void generateServiceAndServiceImpl() throws Exception {
		this.generateService();
		this.generateServiceImpl();
	}

	@Test
	public void generateGroup() throws Exception {
		this.generategroup();
	}




	private void generateDaoimpl() throws IOException {
		PrintWriter writer = this.getWriter(DAOIMPL);
		writer.println("@Repository");
		writer.println("public class " + domainDaoImpl + "  implements  "+domainDao+" {");
		writer.println("");
		writer.println("");
		writer.println("}");
		writer.close();
	}

	/**
	 * 生成service文件
	 * @throws Exception
	 */
	@Test
	public void generateService() throws Exception {
		try {
			PrintWriter writer = this.getWriter(SERVICE);
			writer.println("public interface " + domainService + " {");
			writer.println("");
			writer.println("");
			writer.println("}");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成service impl文件
	 * @throws Exception
	 */
	@Test
	public void generateServiceImpl() throws Exception {
		try {
			PrintWriter writer = this.getWriter(SERVICEIMPL);
			writer.println("@Service");
			writer.println("public class " + domainServiceImpl + " implements "
					+ domainService + " {");
			writer.println("");
			writer.println("\t@Resource");
			writer.println("\tprivate " + domainRepository + " "
					+ domainFirstLower + DAO + ";");
			writer.println("");
			writer.println("}");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成dao文件
	 * @throws Exception
	 */

	@Test
	public void generateRespository() throws Exception {
		try {
			PrintWriter writer = this.getWriter(REPOSITORY);
			writer.println("public interface " + domainRepository
					+ " extends BaseDao<" + domain+"Entity" + ","+domainID+">,"+domain+"Dao"+"{");
			writer.println("");
			writer.println("");
			writer.println("}");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getFirstLower(String domain) {
		char[] chars = domain.toCharArray();
		chars[0] = Character.toLowerCase(chars[0]);
		return String.valueOf(chars);
	}

	private PrintWriter getWriter(String generatorObejctName) throws IOException {
		String packagePath = null;
		String packageName = null;
		String filename = null;

		 if(generatorObejctName.equals(SERVICE)) {
			packageName = basePackageName+".service";
			filename = domainService+".java";
		} else if(generatorObejctName.equals(SERVICEIMPL)) {
			packageName = basePackageName+".service.impl";
			filename = domainServiceImpl+".java";
		 } else if(generatorObejctName.equals(REPOSITORY)) {
			packageName = basePackageName+".repository";
			filename = domainRepository+".java";
		}else if(generatorObejctName.equals(DAO)) {
			 packageName = basePackageName+".dao";
			 filename = domainDao+".java";
		 }
		 else if(generatorObejctName.equals(DAOIMPL)) {
			 packageName = basePackageName+".dao.impl";
			 filename = domainDaoImpl+".java";
		 }
		 else if(generatorObejctName.equals(GROUP)) {
			 packageName = basePackageName+".group";
			 filename = domainGroup+".java";
		 }
		 else {
			throw new RuntimeException("Invalid arguments " + generatorObejctName);
		}
		packagePath = packageName.replaceAll("\\.", "/");
		String path = home+"/"+packagePath+"/"+filename;
		File file = new File(path);
		if (!file.exists()) {
			System.out.println(file.getPath());
			file.createNewFile();
		} else {
			throw new IOException("File already exists :" + path);
		}
		PrintWriter writer = new PrintWriter(file);
		writer.println("package "+ packageName +";");
		writer.println("");
		return writer;
	}

	public static void main(String[] args) {
  String a="1";
  String b="1";
		System.out.println(a.equals(b));
	}
}
