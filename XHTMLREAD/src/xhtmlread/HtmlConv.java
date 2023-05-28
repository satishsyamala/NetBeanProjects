/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xhtmlread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

/**
 *
 * @author Satish
 */
public class HtmlConv {

    /**
     * @param args the command line arguments
     */
    public int progressCount = 0;
    public int totalFile = 0;
    public int totalWords = 100;
    public int progressWordCount = 0;
    public String proText = "Started";
    public String proWord = "Waitong";

    public static void main(String[] args) throws Exception {
        HtmlConv h = new HtmlConv();
        if (false) {
            Map<String, String> map = new HashMap<>();
            File md = new File("E:\\html\\pages");
            for (File f : md.listFiles()) {
                if (f.isFile() && f.getAbsolutePath().endsWith(".xhtml")) {
                    Map<String, String> map1 = h.changeFile(h.method(f.getAbsolutePath()), f);
                    map.putAll(map1);
                }
            }

            //  System.out.println(map);
            h.generatePropertyFile(map, "E:\\html\\message-en.properties", 0);
            h.generatePropertyFileTelugu(map, "E:\\html\\message-te.properties", "te", "");
        } else if (false) {
            Map<String, String> map = new HashMap<>();
            List<String> ls = h.readFile("E:\\html\\keys.txt");
            for (String s : ls) {
                String key = h.getKey(s, "");
                map.put(key, h.transletString(s, "te"));
            }
            h.generatePropertyFile(map, "E:\\html\\key-te.properties", 0);
        } else {
            // System.out.println(h.fileCount("E:\\html\\a", new ArrayList<>()).size());
            h.writeFileDeffi("E:\\html\\a", "E:\\html\\z", h.fileCount("E:\\html\\a", new ArrayList<>()));
        }
    }

    public int getTotalNo(String sf, String lan) {
        totalFile = fileCount(sf);
        return totalFile;
    }

    public int fileCount(String path) {
        int fileCount = 0;
        File md = new File(path);
        for (File f : md.listFiles()) {
            if (f.isDirectory()) {
                fileCount = fileCount + fileCount(f.getAbsolutePath());
            } else {
                if (f.isFile() && (f.getAbsolutePath().endsWith(".xhtml") || f.getAbsolutePath().endsWith(".txt"))) {
                    fileCount++;
                }
            }
        }
        return fileCount;
    }

    public void writeFileDeffi(String basePath, String newPath, List<String> files) {
        for (String s : files) {
            String me = s.replace(basePath, newPath);
            File f = new File(me);
            if (!f.getParentFile().isDirectory()) {
                f.getParentFile().mkdir();
            }
        }
    }

    public List<String> fileCount(String path, List<String> fileCount) {
        File md = new File(path);
        for (File f : md.listFiles()) {
            if (f.isDirectory()) {
                fileCount(f.getAbsolutePath(), fileCount);
            } else {
                if (f.isFile() && (f.getAbsolutePath().endsWith(".xhtml") || f.getAbsolutePath().endsWith(".txt"))) {
                    fileCount.add(f.getAbsolutePath());
                }
            }
        }
        return fileCount;
    }

    public void startProcess(String sf, String lan, String df, String pf, List<String> exl, Map<String, String> tags, String exFile) {
        Map<String, String> map = new HashMap<>();
        List<String> fileLs = fileCount(sf, new ArrayList<>());
        totalFile = fileLs.size();

        progressCount = 0;
        progressWordCount = 0;
        proWord = "Waitong";
        for (String s : fileLs) {
           

            File f = new File(s);
            progressCount++;
            proText = f.getName();
            if (f.isFile() && f.getAbsolutePath().endsWith(".xhtml")) {
                Map<String, String> map1 = changeFile(method(f.getAbsolutePath()), f, exl, tags, df, sf);
                map.putAll(map1);
            } else if (f.isFile() && f.getAbsolutePath().endsWith(".txt")) {
                Map<String, String> map1 = readTxtFile(f);
                map.putAll(map1);
            }
        }
        progressCount = totalFile;
        proText = "Completed";

        //  System.out.println(map);
        System.out.println(lan);

        if (lan.equalsIgnoreCase("en")) {
            totalWords = map.size();
            generatePropertyFile(map, pf + "\\message-en.properties", 1);
        } else {
            totalWords = map.size();
            generatePropertyFile(map, pf + "\\message-en.properties", 0);
            generatePropertyFileTelugu(map, pf + "\\message-" + lan + ".properties", lan, exFile);
        }
      
    }

    public Map<String, String> readTxtFile(File file) {

        Map<String, String> map = new HashMap<>();
        List<String> ls = readFile(file.getAbsolutePath());
        for (String s : ls) {
            String key = getKey(s, "");
            map.put(key, s);
        }
        return map;
    }

    private String method(String filePath) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader buffer = new BufferedReader(
                new FileReader(filePath))) {
            String str;
            while ((str = buffer.readLine()) != null) {
                builder.append(str).append("\n");
            }
        } catch (IOException e) {
        }

        // Returning a string
        return builder.toString();
    }

    public String getKey(String lebel, String fileName) {
        //  return fileName+"@"+lebel.replaceAll(" ", "").toLowerCase();
        //  System.out.println(lebel);
        List<String> rp = new ArrayList<>();
        rp.add(":");
        rp.add(" ");
        rp.add("/");
        rp.add("=");
        rp.add("'");
        for (String s : rp) {

            lebel = lebel.replaceAll(s, "");
            //   System.out.println(s+" : "+lebel);
        }
        // System.out.println("Befor "+lebel);
        return lebel.toLowerCase();
    }

    public List<String> excludeTags() {
        List<String> ls = new ArrayList<>();
        ls.add("style");
        ls.add("script");
        ls.add("scripts");
        return ls;
    }

    public Map<String, String> changedTags() {
        Map<String, String> map = new HashMap<>();
        map.put("p:outputlabel", "value");
        map.put("h:outputtext", "value");
        map.put("p:column", "headerText");
        map.put("f:selectitem", "itemLabel");
        map.put("p:commandbutton", "value");

        return map;
    }

    public boolean valueCheck(String data) {
        if (data != null && data.trim().length() > 0 && !data.trim().contains("{")) {
            return true;
        } else {
            return false;
        }
    }

    public String transletString(String s, String ln) {
        try {
            String urlStr = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=" + ln + "&dt=t&q=" + URLEncoder.encode(s, "UTF-8");
            URL url = new URL(urlStr);
            StringBuilder response = new StringBuilder();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONParser j = new JSONParser();
            JSONArray a = (JSONArray) j.parse(response.toString());
            JSONArray a1 = (JSONArray) a.get(0);
            JSONArray a2 = (JSONArray) a1.get(0);
            return a2.get(0).toString();
        } catch (Exception e) {

        }
        return s;
    }

    public Map<String, String> changeFile(String file, File ff) {
        Map<String, String> map = new HashMap<>();
        try {

            Document doc = Jsoup.parse(file, "", Parser.xmlParser());
            //   System.out.println(ff.getName());
            Elements allElements
                    = doc.getAllElements();
            List<String> excludeTags = excludeTags();
            Map<String, String> tags = changedTags();
            if (true) {
                for (Element element : allElements) {
                    if (!excludeTags.contains(element.nodeName().toLowerCase())) {
                        if (tags.containsKey(element.nodeName().toLowerCase())) {
                            String pro = tags.get(element.nodeName().toLowerCase());
                            List<Attribute> al = element.attributes().asList();
                            for (Attribute a : al) {
                                if (a.getKey().equalsIgnoreCase(pro) && valueCheck(a.getValue())) {
                                    String mseg = a.getValue();
                                    String key = getKey(mseg, ff.getName());
                                    if (key.trim().length() > 0) {
                                        map.put(key, mseg);
                                        a.setValue("#{msg['" + key + "']}");
                                    }
                                }
                            }
                        } else if (element.nodeName().equalsIgnoreCase("br")) {
                            element.text(" ");
                        } else {
                            if (element.ownText() != null && valueCheck(element.ownText())) {
                                String mseg = element.ownText();
                                String key = getKey(mseg, ff.getName());
                                if (key.trim().length() > 0) {
                                    map.put(key, mseg);
                                    element.text("");
                                    element.appendElement("h:outputText").attr("value", "#{msg['" + key + "']}");
                                }
                            }
                        }
                    }
                }

            } else {
                for (Element element : allElements) {
                    System.out.println(element.nodeName());
                }
            }
            FileUtils.fileWrite("E:\\html\\CHANGE\\" + ff.getName(), doc.outerHtml());
        } catch (Exception e) {

        }
        return map;
    }

    public Map<String, String> changeFile(String file, File ff, List<String> excludeTags, Map<String, String> tags, String df) {
        Map<String, String> map = new HashMap<>();
        try {

            Document doc = Jsoup.parse(file, "", Parser.xmlParser());
            System.out.println(ff.getName());
            Elements allElements
                    = doc.getAllElements();

            if (true) {
                for (Element element : allElements) {
                    if (!excludeTags.contains(element.nodeName().toLowerCase())) {
                        if (tags.containsKey(element.nodeName().toLowerCase())) {
                            String pro = tags.get(element.nodeName().toLowerCase());
                            List<Attribute> al = element.attributes().asList();
                            for (Attribute a : al) {
                                if (a.getKey().equalsIgnoreCase(pro) && valueCheck(a.getValue())) {
                                    String mseg = a.getValue();
                                    String key = getKey(mseg, ff.getName());
                                    if (key.trim().length() > 0) {
                                        map.put(key, mseg);
                                        a.setValue("#{msg['" + key + "']}");
                                    }
                                }
                            }
                        } else if (element.nodeName().equalsIgnoreCase("br")) {
                            element.text(" ");
                        } else {
                            if (element.ownText() != null && valueCheck(element.ownText())) {
                                String mseg = element.ownText();
                                String key = getKey(mseg, ff.getName());
                                if (key.trim().length() > 0) {
                                    map.put(key, mseg);
                                    element.text("");
                                    element.appendElement("h:outputText").attr("value", "#{msg['" + key + "']}");
                                }
                            }
                        }
                    }
                }

            } else {
                for (Element element : allElements) {
                    System.out.println(element.nodeName());
                }
            }
            FileUtils.fileWrite(df + "\\" + ff.getName(), doc.outerHtml());
        } catch (Exception e) {

        }
        return map;
    }

    public Map<String, String> changeFile(String file, File ff, List<String> excludeTags, Map<String, String> tags, String df, String baseFile) {
        Map<String, String> map = new HashMap<>();
        try {

            Document doc = Jsoup.parse(file, "", Parser.xmlParser());
            System.out.println(ff.getName());
            Elements allElements
                    = doc.getAllElements();

            if (true) {
                for (Element element : allElements) {
                    if (!excludeTags.contains(element.nodeName().toLowerCase())) {
                        if (tags.containsKey(element.nodeName().toLowerCase())) {
                            String pro = tags.get(element.nodeName().toLowerCase());
                            List<Attribute> al = element.attributes().asList();
                            for (Attribute a : al) {
                                if (a.getKey().equalsIgnoreCase(pro) && valueCheck(a.getValue())) {
                                    String mseg = a.getValue();
                                    String key = getKey(mseg, ff.getName());
                                    if (key.trim().length() > 0) {
                                        map.put(key, mseg);
                                        a.setValue("#{msg['" + key + "']}");
                                    }
                                }
                            }
                        } else if (element.nodeName().equalsIgnoreCase("br")) {
                            element.text(" ");
                        } else {
                            if (element.ownText() != null && valueCheck(element.ownText())) {
                                String mseg = element.ownText();
                                String key = getKey(mseg, ff.getName());
                                if (key.trim().length() > 0) {
                                    map.put(key, mseg);
                                    element.text("");
                                    element.appendElement("h:outputText").attr("value", "#{msg['" + key + "']}");
                                }
                            }
                        }
                    }
                }

            } else {
                for (Element element : allElements) {
                    System.out.println(element.nodeName());
                }
            }
            String s = ff.getAbsolutePath().replace(baseFile, df);
            File f = new File(s);
            if (!f.getParentFile().isDirectory()) {
                f.getParentFile().mkdir();
            }
            FileUtils.fileWrite(f.getAbsolutePath(), doc.outerHtml());
        } catch (Exception e) {

        }
        return map;
    }

    public void generatePropertyFile(Map<String, String> map, String fileName, int type) {
        try {
            FileWriter fw = new FileWriter(fileName);
            for (String s : map.keySet()) {
              
                if (type > 0) {
                    progressWordCount++;
                    proWord = s;
                }
                fw.write(s + "=" + map.get(s));
                fw.write("\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void generatePropertyFileTelugu(Map<String, String> map, String fileName, String ln, String epf) {
        try {
            FileWriter fw = new FileWriter(fileName);
            Map<String, String> ep = readPropertyFile(epf);
            System.out.println(epf + " : " + ep.size());
            int count = 0;
            for (String s : map.keySet()) {
                progressWordCount++;
                proWord = s;
                String str = "";
                if (ep.containsKey(s)) {
                    str = ep.get(s);
                } else {
                    str = transletString(map.get(s), ln);
                    System.out.println((count++) + " ==> " + str);
                }
                fw.write(s + "=" + str);
                fw.write("\n");

            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void print(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Map<String, String> readPropertyFile(String file) {
        Map<String, String> map = new HashMap<>();
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                String[] s = data.split("=");
                map.put(s[0], s[1]);
            }
            myReader.close();
        } catch (Exception e) {

        }
        return map;
    }

    public List<String> readFile(String file) {
        List<String> map = new ArrayList<>();
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                map.add(data);
            }
            myReader.close();
        } catch (Exception e) {

        }
        return map;
    }

    public int getProgressCount() {
        return progressCount;
    }

    public void setProgressCount(int progressCount) {
        this.progressCount = progressCount;
    }

    public String getProText() {
        return proText;
    }

    public void setProText(String proText) {
        this.proText = proText;
    }

    public int getTotalFile() {
        return totalFile;
    }

    public void setTotalFile(int totalFile) {
        this.totalFile = totalFile;
    }

    public int getTotalWords() {
        return totalWords;
    }

    public void setTotalWords(int totalWords) {
        this.totalWords = totalWords;
    }

    public int getProgressWordCount() {
        return progressWordCount;
    }

    public void setProgressWordCount(int progressWordCount) {
        this.progressWordCount = progressWordCount;
    }

    public String getProWord() {
        return proWord;
    }

    public void setProWord(String proWord) {
        this.proWord = proWord;
    }

}
