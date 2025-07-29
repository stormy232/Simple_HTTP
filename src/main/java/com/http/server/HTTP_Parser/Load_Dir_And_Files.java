package HTTP_Parser;

import java.io.File;

public class Load_Dir_And_Files{
    
    public static String HTML(String [] Files, String Path){
        String Template = "<!Doctype Html>\n<html>\n<title> %s </title>\n";
        String Result = String.format(Template,Path);
        StringBuilder Table = new StringBuilder();

        for(int i = 0; i < Files.length; i++){
            String Row = "<table> <td> <a href=%S> %S </a> </td>\n </table>";
            String File = Files[i];
            Table.append(String.format(Row,File,File));
        }
        System.out.println(Result + Table.toString());
        return (Result + Table.toString() + "</body>\n</html>");
    }

    public static String[] List_Files(String Path){
        File Directory = new File(Path);
        return Directory.list();
    }

   /* public static void main(String[] args) {
        String Path = ".";
        String[] Files = List_Files(Path);
        System.out.println(HTML(Files, Path));
    } */
}