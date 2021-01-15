public class Student implements Student_ {
    String first;
    String last;
    String hostel;
    String department;
    String cg;
    //Pair<String, String> keypair;

    Student(String firstname,String lastname,String Hostel,String dep,String cgpa){
        //String[] arr = name.split("_");
        first = firstname;
        last = lastname;
        hostel = Hostel;
        department = dep;
        cg = cgpa;
    }

    public String fname() {
        return first;
    }

    public String lname() {
        return last;
    }

    public String hostel() {
        return hostel;
    }

    public String department() {
        return department;
    }

    public String cgpa() {
        return cg;
    }

    public String getAll(){
        return this.fname()+" "+this.lname()+" "+this.hostel()+" "+this.department()+" "+this.cgpa();
    }

    public String toString(){
        return this.fname();    /// in fact return concatenation of first name and second name//
    }



}
