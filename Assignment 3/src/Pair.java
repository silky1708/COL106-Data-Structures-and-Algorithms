class Pair<U,V> implements Comparable<Student> {
    private U first;
    private V second;
    Pair(U t, V v){
        first = t;
        second = v;
    }
    public String toString(){
        return first.toString()+second.toString();
    }
    @Override
    public int compareTo(Student o) {
        if(this.first.toString().compareTo(o.toString())>0){
            return 1;
        }
        else if(this.first.toString().compareTo(o.toString())<0){
            return -1;
        }
        else return 0;
    }
}
