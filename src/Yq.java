import java.io.*;
import java.util.ArrayList;
import java.util.List;


class Yq{
    public static void main(String argv[]){
        try {
            String filePathIn = "yq_in.txt";
            String filePahtOut = "yq_out.txt";
            String encoding="GBK"; //编码格式
            List<String> str = new ArrayList<String>();
            File file=new File(filePathIn);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                
                String lineTxt = null;
                FileWriter fwriter = new FileWriter(filePahtOut, true);
                String temp = "";
                // 读取文件
                while((lineTxt = bufferedReader.readLine()) != null){
                     if(!temp.equals(lineTxt.substring(0, 3))){
                        temp = lineTxt.substring(0, 3);
                        // 写入文件
                        fwriter.write(temp+"\r\n");
                    }
                    if(!item.substring(4, lineTxt.length()).equals("待明确地区	0")){
                        // 写入文件
                        fwriter.write(item.substring(4, lineTxt.length())+"\r\n");
                    }
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
}