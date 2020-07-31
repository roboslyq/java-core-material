package com.roboslyq.tu;
import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TU定长文件解析
 * TODO: 目前是单线程，可以优化
 * @author roboslyq
 * @date 2020/7/24
 * @since 1.0.0
 */
public class TuFileParse {

    /** 源文件路径: 不需要日期，程序中会自己追加跑批日期*/
    private static String SRC_FILE_PATH;
    /** 结果文件保存路径，可以从参数中传递: 不需要日期，程序中会自己追加跑批日期 */
    private static String RES_FILE_PATH;
    /** 当前位置*/
    private static int currIndex = 85;
    /** 结果文件后缀*/
    private static String RES_FILE_SUFFIX = ".dat";
    /** OK文件后缀 */
    private static String OK_FILE_SUFFIX = ".ok";
    /** 报表日期*/
    private static String date;
    /** TU文件名称*/
    private static String fileName;
    /** 计数器（行号）*/
    private static AtomicInteger countNum = new AtomicInteger(0);
    /** 模块名称: 一共5个模块，对应5个不同文件名称*/
    private static Map<String, String> MODULE_SEQ = new HashMap<>();
    /** Segments：每1个文件格式一样，由7个组成。segments数组*/
    private static String[] SEGS = {"NA", "AL", "AD", "PH", "AC", "LM", "RI"};
    /** 约定，每1个Segments对应一个编号*/
    private static Map<String, String> SEGMENT_SEQ = new HashMap<>();

    /** 每一个Segments对应的字段(TU已经规定好格式，不能改 ) */
    private static Map<String, String[]> SEGMENT_FIELD = new HashMap<>();
    private static String[] NA_LIST = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
    private static String[] AL_LIST = {"03", "05", "06", "07", "08", "09", "10"};
    private static String[] AD_LIST = {"01", "02", "03", "04"};
    private static String[] PH_LIST = {"01", "02", "03", "04", "05"};
    private static String[] AC_LIST = {"01", "02", "04", "05", "07", "08", "09", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "30", "32", "33", "34", "35"};
    private static String[] LM_LIST = {"01", "02"};
    private static String[] RI_LIST = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"};
    /**结果*/
    private static Map<String, List<String>> RESULT_MAP = new HashMap<>();

    static {
        MODULE_SEQ.put("cif088d.dat", "02");
        MODULE_SEQ.put("cif089rp.dat", "03");
        MODULE_SEQ.put("cif089rn.dat", "04");
        MODULE_SEQ.put("cif089r1.dat", "05");
        MODULE_SEQ.put("cif089r2.dat", "06");

        SEGMENT_SEQ.put("NA", "01");
        SEGMENT_SEQ.put("AL", "02");
        SEGMENT_SEQ.put("AD", "03");
        SEGMENT_SEQ.put("PH", "04");
        SEGMENT_SEQ.put("AC", "05");
        SEGMENT_SEQ.put("LM", "06");
        SEGMENT_SEQ.put("RI", "07");

        SEGMENT_FIELD.put("NA", NA_LIST);
        SEGMENT_FIELD.put("AL", AL_LIST);
        SEGMENT_FIELD.put("AD", AD_LIST);
        SEGMENT_FIELD.put("PH", PH_LIST);
        SEGMENT_FIELD.put("AC", AC_LIST);
        SEGMENT_FIELD.put("LM", LM_LIST);
        SEGMENT_FIELD.put("RI", RI_LIST);

        RESULT_MAP.put("NA", new ArrayList<>());
        RESULT_MAP.put("AL", new ArrayList<>());
        RESULT_MAP.put("AD", new ArrayList<>());
        RESULT_MAP.put("PH", new ArrayList<>());
        RESULT_MAP.put("AC", new ArrayList<>());
        RESULT_MAP.put("LM", new ArrayList<>());
        RESULT_MAP.put("RI", new ArrayList<>());
    }

    /**
     * 解析文件入口
     * @param filePath 包括文件路径和具体名称
     * @throws IOException 文件解析异常
     */
    private void parseFile(String filePath) throws IOException {
        File srcFile = new File(filePath);
        if(!srcFile.exists()){
            System.out.println("源文件:" + srcFile + "不在存!!!");
            return;
        }
        BufferedReader fileReader = new BufferedReader(new FileReader(srcFile));
        String  completeStr = fileReader.readLine();
        // 头部长度
        int headSize = 85;
        if(Objects.isNull(completeStr) || completeStr.length() <= headSize){
            System.out.println("文件数据长度小于85,直接生成结果文件.");
        }else{
            // 文件中的日期格式为:DDMMYYYY
            String dateInFile = completeStr.substring(71,75) + completeStr.substring(69,71) + completeStr.substring(67,69);
            if(!dateInFile.equals(date)){
                System.out.println("跑批日期：" + date + "与文件日期：" + dateInFile +"不匹配！！！");
                return;
            }
            String srcdata = completeStr.substring(headSize);
            // 按每一笔Loan拆分，得到每一笔Loan的详情，包含7个segments
            String[] resArray = srcdata.split("ES02\\*\\*");
            Arrays.stream(resArray).forEach(loan -> {
                int curCountNum = countNum.getAndIncrement();
                parseLoan(loan, 0, "",curCountNum);
            });
        }
        writeResult(RESULT_MAP);
        System.out.println("parsed the file successfully!");
    }

    /**
     * 解析每一笔Loan
     *
     * @param loan 每一个贷款记录
     */
    private void parseLoan(String loan, int curIndex, String naLineno,int curCountNum) {
        if (curIndex == loan.length()){
            return;
        }
        //  the current segment name: NA,AL,AD,PH,AC,LM,RI
        String segName = loan.substring(curIndex, curIndex + 2);
        int segLength = Integer.parseInt(loan.substring(curIndex + 2, curIndex + 4));
        String segmentLine = loan.substring(curIndex + 4, curIndex + 4 + segLength);
        String[] fields = SEGMENT_FIELD.get(segName);
        StringBuilder sb = new StringBuilder();
        //分割符
        String delimiter = "|";
        sb.append(date).append(delimiter).append(curCountNum).append(delimiter);
        if (segName.equals(SEGS[0])) {
            naLineno = segmentLine;
        } else {
            sb.append(naLineno).append(delimiter);
        }
        sb.append(segmentLine).append(delimiter);

        curIndex = curIndex + 4 + segLength;
        // 循环处理各种域
        for (String field : fields) {
            if (curIndex == loan.length()) {
                //追加空的分割符
                sb.append(delimiter);
                continue;
            }
            String fieldInLoan = loan.substring(curIndex, curIndex + 2);
            if (!fieldInLoan.equals(field)) {
                sb.append(delimiter);
            } else {
                int fieldLen = Integer.parseInt(loan.substring(curIndex + 2, curIndex + 2 + 2));
                if (fieldLen == 0) {
                    sb.append(delimiter);
                    curIndex += 4;
                } else {
                    String fieldValue = loan.substring(curIndex + 4, curIndex + 4 + fieldLen);
                    curIndex = curIndex + 4 + fieldLen;
                    sb.append(fieldValue).append(delimiter);
                }
            }
        }
        RESULT_MAP.get(segName).add(sb.toString());
        // 递归解析
        parseLoan(loan, curIndex, naLineno,curCountNum);
    }

    /**
     * 将结果写入文件
     * @param content  结果内容
     * @throws IOException 写异常
     */
    private void writeResult(Map<String,List<String>> content) throws IOException {
        for(String seg :SEGS){
            List<String> list =  content.get(seg);
            fileWriter(seg,list);
        }
    }

    /**
     * 初始化
     */
    private static void init(String[] args){
        TuFileParse.fileName = args[0];
        TuFileParse.date = args[1];
        TuFileParse.SRC_FILE_PATH = args[2];
        TuFileParse.RES_FILE_PATH = args[3];
        // 循环清除上一次跑批的结果文件,如果是第1次运行,则不需要删除
        for(String seg :SEGS){
            String filePathAndName =RES_FILE_PATH + getResultFileName(seg,RES_FILE_SUFFIX);
            String okFilePathAndName = RES_FILE_PATH + getResultFileName(seg,OK_FILE_SUFFIX);
            System.out.println("要删除的文件名：" + filePathAndName);
            File file = new File(filePathAndName);
            File okFile = new File(okFilePathAndName);
            if(file.exists()){
                boolean fileDel = file.delete();
                boolean okFileDel = okFile.delete();
                System.out.println("结果文件删除： " + fileDel +", .ok文件删除：" + okFileDel);
            }else {
                System.out.println("结果文件" + filePathAndName +"不存在,不需要删除");
            }

        }

    }
    /**
     * 使用字符流
     */
    private void fileWriter(String seg, List<String> content) throws IOException {
        File file = new File(RES_FILE_PATH + getResultFileName(seg,RES_FILE_SUFFIX));
        boolean createNewFileResult = file.createNewFile();
        if (!createNewFileResult) {
            System.out.println("文件创建失败!!");
            return;
        }
        // 覆盖写
        // FileWriter out = new FileWriter(file);
        // 追加写
        FileWriter out = new FileWriter(file, true);
        for (String line : content) {
            out.write(line + "\n");
        }
        /* 必须刷新,否则部分数据无法写回文件。  注意,是部分数据,当达到缓冲区存储值,会自动 刷新缓冲区。*/
        out.flush();
        File okFile = new File(RES_FILE_PATH + getResultFileName(seg,OK_FILE_SUFFIX));
        boolean okFileRes = okFile.createNewFile();
        if (!okFileRes) {
            System.out.println(".ok 文件创建失败!!");
        }
    }

    /**
     * 根据seg获取结果文件路径
     * @param seg seg名称
     * @return 文件名称
     */
    private static String getResultFileName(String seg,String suffix){
        return seg + "-" + MODULE_SEQ.get(fileName) + SEGMENT_SEQ.get(seg)  + "-" + date + suffix;
    }


    public static void main(String[] args) throws IOException {
        int argsNum = 4;
        if(args.length < argsNum){
            System.out.println("请使用命令`java -jar xxx.jar filename report_date srcdir resdir`来启动。\n" +
                    "例如 `java -jar cif089rp.dat 20200331  D:\\logs\\tu\\20200731\\  D:\\logs\\tu\\res\\20200731\\`");
            System.exit(1);
        }

        init(args);

        TuFileParse fileDemo = new TuFileParse();

        long startTime = System.currentTimeMillis();
        System.out.println("start pasre timestamp (ms): " + startTime);
        fileDemo.parseFile(SRC_FILE_PATH + fileName);
        long endTime = System.currentTimeMillis();
        System.out.println("end parse  timestamp (ms): " + endTime);
        System.out.println("used millis time: " + ( endTime - startTime) + "(ms)");

    }

}
