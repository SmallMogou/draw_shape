/**
    SE实践4
    @author lm
    since 1.0
 */
import java.io.*;
import java.text.Collator;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Yq{
    public static void main(String argv[]){
        try {
            String filePathIn = "yq_in_04.txt";
            String filePathOut = "yq_out_04.txt";
            String encoding="GBK"; //编码格式
            File file=new File(filePathIn);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                FileWriter fwriter = new FileWriter(filePathOut, false);
                
                YqInfo yqInfo = null;
                ArrayList<Area> areas = null;
                String lineTxt = null;
                ArrayList<YqInfo> yqInfos = new ArrayList<YqInfo>();
                String[] tempArr = null;
                String temp = "";
                while((lineTxt = bufferedReader.readLine()) != null){ //读取文件
                    tempArr = lineTxt.split("\\s+");
                    if(!temp.equals(tempArr[0])){
                        temp = tempArr[0];
                        areas = new ArrayList<Area>();
                        yqInfo = new YqInfo(temp, 0, areas);
                        yqInfos.add(yqInfo); // 存入yqInfos
                    }
                    if(!(tempArr[1]).equals("待明确地区")){ // 存入一个地区
                        yqInfo.getAreas().add(new Area(tempArr[1], Integer.valueOf(tempArr[2])));
                        yqInfo.setCaseSum(yqInfo.getCaseSum() + Integer.valueOf(tempArr[2]));
                    }
                }
                
                YqInfo[] yqArr = YqInfo.sortProvinceByCaseSum(yqInfos);
                Area[] areaArr = null;
                for(YqInfo y : yqArr){ //写入文件
                    fwriter.write(y.getProvince()+ " " + "总数：" + y.getCaseSum() + "\r\n");
                    areaArr = Area.sortAreaByCases(y.getAreas());
                    for(Area a: areaArr)
                        fwriter.write(a.getArea() + " " +a.getCases() + "\r\n");
                }
                    
                read.close();
                fwriter.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }
    /**
        汉字拼音排序
        @author lm
        since 2.0
     */
    public static void sortByPinyinCode(Object[] objs){
        String[] strArr = new String[2];
        YqInfo[] tmpYqInfoArr = null;
        Area[] tmpAreaArr = null;
        if(objs instanceof YqInfo[]){
            tmpYqInfoArr = (YqInfo[])objs; 
            strArr[0] = tmpYqInfoArr[0].getProvince();
            strArr[1] = tmpYqInfoArr[1].getProvince();
        }else{
            tmpAreaArr = (Area[])objs;
            strArr[0] = tmpAreaArr[0].getArea();
            strArr[1] =  tmpAreaArr[1].getArea();
        }
        Comparator cmp = Collator.getInstance(java.util.Locale.CHINA);
        Arrays.sort(strArr, cmp);
        if(objs instanceof YqInfo[]){
            tmpYqInfoArr[0].setProvince(strArr[0]);
            tmpYqInfoArr[1].setProvince(strArr[1]);
        }else{
            tmpAreaArr[0].setArea(strArr[0]);
            tmpAreaArr[1].setArea(strArr[1]);
        }
    }
}

/**
    一个省的信息
    @author lm
    since 2.0
 */
class YqInfo{
    private String province;
    private int caseSum;
    private ArrayList<Area> areas;

    public YqInfo(String province, int caseSum, ArrayList<Area> areas){
        this.province = province;
        this.caseSum = caseSum;
        this.areas = areas;
    }

    public String getProvince(){
        return this.province;
    }

    public int getCaseSum(){
        return this.caseSum;
    }

    public ArrayList<Area>  getAreas(){
        return this.areas;
    }

    public void setProvince(String province){
        this.province = province;
    }

    public void setCaseSum(int caseSum){
        this.caseSum = caseSum;
    }

    public void setAreas(ArrayList<Area> areas){
        this.areas = areas;
    }
    
    /**
        按照病例数从大到小排序省份
        @author lm
        since v2.0
     */
    public static YqInfo[] sortProvinceByCaseSum(ArrayList<YqInfo> yqInfos){
        YqInfo[] yqArr = yqInfos.toArray(new YqInfo[yqInfos.size()]);
        YqInfo[] yqArr2 = new YqInfo[2];
        YqInfo temp = null;
        for(int i = 0; i < yqArr.length; i++){
            for(int j = i + 1; j < yqArr.length; j++){
                if(yqArr[i].getCaseSum() < yqArr[j].getCaseSum()){
                    temp = yqArr[i];
                    yqArr[i] = yqArr[j];
                    yqArr[j] = temp;
                } else if(yqArr[i].getCaseSum() == yqArr[j].getCaseSum()){
                    yqArr2[0] = yqArr[i];
                    yqArr2[1] = yqArr[j]; 
                    Yq.sortByPinyinCode(yqArr2);
                    yqArr[i] = yqArr2[0];
                    yqArr[j] = yqArr2[1];
                }
            }
        }
        return yqArr;
    }
}

/**
    一个地区的信息
    @author lm
    since 2.0
 */
class Area{
    private String area;
    private int cases;

    public Area(String area, int cases){
        this.area = area;
        this.cases = cases;
    }

    public String getArea(){
        return this.area;
    }

    public int getCases(){
        return this.cases;
    }

    public void setArea(String area){
        this.area = area;
    }

    public void seeCases(int cases){
        this.cases = cases;
    }

    /**
        按照病例数从大到小排序地区
        @author lm
        since v2.0
     */
    public static Area[] sortAreaByCases(ArrayList<Area> areas){
        Area[] areaArr = areas.toArray(new Area[areas.size()]);
        Area[] areaArr2 = new Area[2];
        Area temp = null;
        for(int i = 0; i < areaArr.length; i++){
            for(int j = i + 1; j < areaArr.length; j++){
                if(areaArr[i].getCases() < areaArr[j].getCases()){
                    temp = areaArr[i];
                    areaArr[i] = areaArr[j];
                    areaArr[j] = temp;
                } else if(areaArr[i].getCases() == areaArr[j].getCases()){
                    areaArr2[0] = areaArr[i];
                    areaArr2[1] = areaArr[j]; 
                    Yq.sortByPinyinCode(areaArr2);
                    areaArr[i] = areaArr2[0];
                    areaArr[j] = areaArr2[1];
                }
            }
        }
        return areaArr;
    }
}