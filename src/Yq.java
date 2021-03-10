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
                // 读取文件
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    str.add(lineTxt);
                }
                // 写入文件
                FileWriter fwriter = new FileWriter(filePahtOut, true);
                String temp = "";
                for(String item : str){
                    if(!temp.equals(item.substring(0, 3))){
                        temp = item.substring(0, 3);
                        fwriter.write(temp+"\r\n");
                    }
                    if(!item.substring(4, item.length()).equals("待明确地区	0")){
                        fwriter.write(item.substring(4, item.length())+"\r\n");
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