package com.phantom.jfinal.tool;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.*;
import java.net.URL;
import java.util.Map;

/**
 * Created by zzk on 2017-11-29.
 */
public class FreeMarkerTool {

    public static String FREE_MARKER_TEMPLATE_PATH = "src/velocity/";

    /**
     * 填充模板内容
     * @param params
     * @return
     */
    public static String fillTemplateContent(String templateName, Map<String,Object> params){
        URL classpath = Thread.currentThread().getContextClassLoader().getResource("");
        String path = classpath.getPath();
        String templatePath = path + "velocity" + File.separator;

        //创建freemarker配置类
        Configuration configuration = new Configuration();
        //设置编码
        configuration.setDefaultEncoding("UTF-8");

        try {
            //ftl模板文件统一配置到velocity目录下
            TemplateLoader templateLoader = new FileTemplateLoader(new File(templatePath));
            configuration.setTemplateLoader(templateLoader);
            //获取模板并设置模板编码
            Template template = configuration.getTemplate(templateName);
            template.setEncoding("UTF-8");

            Writer out = new StringWriter();
            //生成文件
            template.process(params,out);
            return out.toString();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


}
