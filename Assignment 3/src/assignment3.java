import java.io.*;

public class assignment3 {
    public static void main(String[] args) throws IOException, NotFoundException {
       int sizeoftable = Integer.parseInt(args[0]);
       String approach = args[1];
       String filename = args[2];
       FileReader file = new FileReader(filename);
       BufferedReader buff = new BufferedReader(file);
       String line = buff.readLine();

           if(approach.toUpperCase().equals("SCBST")) {
               SCBST<Pair<String, String>, Student> sep_chain = new SCBST<Pair<String, String>, Student>(sizeoftable);
               while (line != null) {
                   String[] array = line.split(" ");

                   if (array[0].toLowerCase().equals("insert")) {
                       Student stu1 = new Student(array[1], array[2], array[3], array[4], array[5]);
                       Pair<String, String> key1 = new Pair<String, String>(array[1], array[2]);
                       int res = (sep_chain.insert(key1, stu1));
                       if(res>0){
                           System.out.println(res);
                       }
                       else{
                           System.out.println("E");
                       }

                   } else if (array[0].toLowerCase().equals("update")) {

                       Student stu2 = new Student(array[1], array[2], array[3], array[4], array[5]);
                       Pair<String, String> key2 = new Pair<String, String>(array[1], array[2]);
                       int roller = sep_chain.update(key2, stu2);
                       if (roller > 0) {
                           System.out.println(roller);
                       } else System.out.println("E");

                   } else if (array[0].toLowerCase().equals("delete")) {
                       Pair<String, String> key3 = new Pair<String, String>(array[1], array[2]);
                       int retro = sep_chain.delete(key3);
                       // think more about it this is not right....count will increase even though it is not present
                       if (retro > 0) {
                           System.out.println(retro);
                       } else System.out.println("E");
                       //System.out.println();
                   } else if (array[0].toLowerCase().equals("contains")) {
                       Pair<String, String> key4 = new Pair<String, String>(array[1], array[2]);
                       String str1 = (sep_chain.contains(key4)) ? "T" : "F";
                       System.out.println(str1);
                   } else if (array[0].toLowerCase().equals("get")) {
                       Pair<String, String> key5 = new Pair<String, String>(array[1], array[2]);
                       Student stu5 = sep_chain.get(key5);
                       if (stu5 != null)
                           System.out.println(stu5.getAll());
                       else
                           System.out.println("E");

                   } else if (array[0].equals("address")) {
                       Pair<String, String> key6 = new Pair<String, String>(array[1], array[2]);
                       String resin = sep_chain.address(key6);
                       if (resin != null) {
                           System.out.println(resin);
                       } else System.out.println("E");

                   }
                   line = buff.readLine();
               }
           }
           else if(approach.toUpperCase().equals("DH")){
               DH<Pair<String, String>,Student> dh = new DH<Pair<String, String>,Student>(sizeoftable);
               while(line!=null){

               String[] array = line.split(" ");
               if(array[0].toLowerCase().equals("insert")){
                   Student stu1 = new Student(array[1],array[2],array[3],array[4],array[5]);
                   Pair<String,String> key1 = new Pair<String, String>(array[1],array[2]);
                   int q = (dh.insert(key1, stu1));
                   if(q>0){
                       System.out.println(q);
                   }
                   else{
                       System.out.println("E");
                   }
               }
               else if(array[0].toLowerCase().equals("update")){
                   Student stu2 = new Student(array[1],array[2], array[3], array[4], array[5]);
                   Pair<String,String> key2 = new Pair<String, String>(array[1], array[2]);
                   int pooh = dh.update(key2, stu2);
                   if(pooh>0){
                       System.out.println(pooh);
                   }
                   else System.out.println("E");

               }
               else if(array[0].toLowerCase().equals("delete")){
                   Pair<String, String> key3 = new Pair<String, String>(array[1],array[2]);
                   int dell = dh.delete(key3);
                   if(dell>0){
                       System.out.println(dell);
                   }
                   else System.out.println("E");

               }
               else if(array[0].toLowerCase().equals("contains")){
                   Pair<String, String> key4 = new Pair<String, String>(array[1], array[2]);
                   String str1 = (dh.contains(key4))?"T":"F";
                   System.out.println(str1);

               }
               else if(array[0].toLowerCase().equals("get")){
                   Pair<String, String> key5 = new Pair<String, String>(array[1], array[2]);
                   Student stu5 = dh.get(key5);
                   if(stu5 == null)
                       System.out.println("E");
                   else
                       System.out.println(stu5.getAll());

               }
               else if(array[0].toLowerCase().equals("address")){
                   Pair<String, String> key6 = new Pair<String, String>(array[1], array[2]);
                   String address = dh.address(key6);
                   //System.out.println(dh.address(key6));
                   int value = Integer.parseInt(address);
                   if(value<0){
                       System.out.println("E");
                   }
                   else{
                       System.out.println(address);
                   }
               }
                   line = buff.readLine();
           }
       }

    }
}
