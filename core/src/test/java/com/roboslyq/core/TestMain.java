package com.roboslyq.core;

/**
 * 责任链模式
 * @author Administrator
 *
 */
public class TestMain {

    public static void main(String[] args) {

        Handler handler = new CurrentHandler2();


        Handler handler3 = new CurrentHandler3();

        handler.setNextHandler(handler);
        handler3.setNextHandler(handler3);
        Response response = handler3.handlerRequest(new Request(new Level(SchoolsEnum.DUOJIE)));

    }

}

class Level{

    SchoolsEnum schoolsEnum;
    public Level(SchoolsEnum schoolsEnum) {
        super();
        this.schoolsEnum = schoolsEnum;
    }
    public boolean above(Level level) {

        if(this.schoolsEnum == level.schoolsEnum) {

            return true;
        }
        return false;
    }
}
class Request{
    Level level;

    public Request(Level level) {
        super();
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }
}

class Response{

}

abstract class Handler{

    private Handler nextHandler;
    public final Response handlerRequest(Request request) {

        Response response = null;

        if(this.handlerLevel().above(request.getLevel())) {

            response = this.response(request);
        }else {

            if(this.nextHandler != null ){
                this.nextHandler.handlerRequest(request);
            }else {
                System.out.println("没有合适的处理器");
            }
        }
        return response;

    }
    public Handler getNextHandler() {
        return nextHandler;
    }
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    protected abstract Level handlerLevel();
    public abstract Response response(Request request);

}

class CurrentHandler2 extends Handler{

    @Override
    protected Level handlerLevel() {
        return  new Level(SchoolsEnum.DUOJIE);
    }

    @Override
    public Response response(Request request) {
        System.out.println("***********SchoolsEnum.DUOJIE)处理器2*******************");
        return null;
    }
}
class CurrentHandler3 extends Handler{

    @Override
    protected Level handlerLevel() {
        // TODO Auto-generated method stub
        return  new Level(SchoolsEnum.AISIJIANGHEMEI);
    }

    @Override
    public Response response(Request request) {

        System.out.println("**********************SchoolsEnum.AISIJIANGHEMEI处理器三正在进行**********************");
        return null;
    }


}
enum SchoolsEnum {

    /**
     * 多艺城西校区
     */
    DUOYIWEST(1,"duoyiwest"),

    /**
     * 西溪校区=多艺五常
     */
    DUOYIXIXI(2,"duoyiwest"),

    /**
     * 多艺滨江校区
     */
    DUOYIBJ(3,"duoyibinjiang"),
    /**
     * duojie=linping
     * 临平校区
     */
    DUOJIE(4,"linping"),
    /**
     * 多艺运河校区
     */
    DUOYIXIAOHE(5,"duoyixiaohe"),

    /**
     * 艾思城西校区
     */
    AISIWEST(6,"aisiwest"),
    /**
     * 利星校区
     */
    LIXING(7,"lixing"),
    /**
     * 多艺江和美校区
     */
    JIANGHEMEI(8,"jianghemei"),
    /**
     * 艾思江和美校区
     */
    AISIJIANGHEMEI(9,"aisijianghemei"),

    /**
     * 西溪龙湖天街校区
     */
    XIXILONGHUTIANJIE(12,"xixilonghu");

    private String name;
    /**
     * 数据库学校id
     */
    private Integer no;

    SchoolsEnum(Integer no, String name) {

        this.no = no;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }


}
