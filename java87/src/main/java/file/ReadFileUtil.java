package file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @date: 2021/12/3 10:55
 */
public class ReadFileUtil {
    public final static String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 读取文件内容
     * <p>
     * 文件可能直接位于本地磁盘中或、classpath，甚至是 jar 中
     *
     * @param filePath
     * @return
     */
    public static String readFileContent(String filePath) {
        String fileContent = null;

        try {
            fileContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        } catch (Throwable e) {
        }

        if (StringUtils.isNotBlank(fileContent)) {
            return fileContent;
        }

        try {
            URL fileUrl = searchAndGetFileFromClasspath(filePath);
            fileContent = org.apache.commons.io.FileUtils.readFileToString(new File(fileUrl.getPath()), StandardCharsets.UTF_8);
        } catch (Throwable e) {
        }

        if (StringUtils.isNotBlank(fileContent)) {
            return fileContent;
        }

        String filePathWithoutClassPath;
        if (filePath.startsWith(CLASSPATH_URL_PREFIX)) {
            filePathWithoutClassPath = filePath.substring(CLASSPATH_URL_PREFIX.length());
        } else {
            filePathWithoutClassPath = filePath;
        }
        if (!filePathWithoutClassPath.startsWith(File.separator)) {
            filePathWithoutClassPath = File.separator + filePathWithoutClassPath;
        }
        try {
            fileContent = IOUtils.resourceToString(filePathWithoutClassPath, StandardCharsets.UTF_8);
        } catch (Throwable e) {
        }
        return fileContent;
    }

    private static URL searchAndGetFileFromClasspath(String resourceLocation) throws FileNotFoundException {
        if (resourceLocation.startsWith(CLASSPATH_URL_PREFIX)) {
            String path = resourceLocation.substring(CLASSPATH_URL_PREFIX.length());
            ClassLoader cl = getDefaultClassLoader();
            URL url = (cl != null ? cl.getResource(path) : ClassLoader.getSystemResource(path));
            if (url == null) {
                String description = "class path resource [" + path + "]";
                throw new FileNotFoundException(description + " cannot be resolved to URL because it does not exist");
            }
            return url;
        }
        try {
            return new URL(resourceLocation);
        } catch (MalformedURLException ex) {
            try {
                return new File(resourceLocation).toURI().toURL();
            } catch (MalformedURLException ex2) {
                throw new FileNotFoundException("Resource location [" + resourceLocation + "] is neither a URL not a well-formed file path");
            }
        }
    }

    private static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = ReadFileUtil.class.getClassLoader();
            if (cl == null) {
                // getClassLoader() returning null indicates the bootstrap ClassLoader
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (Throwable ex) { // NOSONAR
                    // Cannot access system ClassLoader - oh well, maybe the caller can live with null...
                }
            }
        }
        return cl;
    }

    public static void main(String[] args) {
        System.out.println(readFileContent("all_neibu_template.json"));
    }
}
