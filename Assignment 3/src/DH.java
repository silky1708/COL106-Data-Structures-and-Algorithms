public class DH<K,T> implements MyHashTable_<K,T> {
    int count;
    private int size;
    Node<T>[] hashtable;
    DH(int tablesize){
      count =0;
      size = tablesize;
      hashtable = new Node[size];
    }
    @Override
    public int insert(K key, T obj) {
        if(this.contains(key)){
            return -1;
        }
        int count =0;
        int index = (int) Hash.djb2(key.toString(), size);
        while(hashtable[index]!=null){
            count++;
                int h1 = (int)Hash.djb2(key.toString(),size);
                int h2 = (int)Hash.sdbm(key.toString(),size);
                index = (h1 + count*h2)%size;
        }
        Node<T> newnode = new Node<T>(obj);
        newnode.first_name = obj.toString();
        newnode.second_name = key.toString().substring(newnode.first_name.length());
        hashtable[index] = newnode;

        return count+1;
    }

    @Override
    public int update(K key, T obj) {  ////assuming input is valid....consider the corner cases separately...
        int count=0;
        Node<T> updated_node = new Node<T>(obj);
        updated_node.first_name = obj.toString();
        updated_node.second_name = key.toString().substring(obj.toString().length());
        int index = (int)Hash.djb2(key.toString(),size);
        //System.out.println(index);
        if(this.contains(key)){
            while(true){
                if(this.hashtable[index]!=null){
                    Node<T> node = hashtable[index];
                    if((node.first_name+node.second_name).compareTo(key.toString())==0){
                        count++;
                        this.hashtable[index] = updated_node;
                        //System.out.print("value matched at");
                        //System.out.println(index);
                        return count;
                    }
                    else{
                        count++;
                        //System.out.print("value doesnot match at");
                        //System.out.println(index);
                        int h1 = (int)Hash.djb2(key.toString(),size);
                        int h2 = (int)Hash.sdbm(key.toString(),size);
                        index = (h1 + count*h2)%size;
                    }
                }
                else{
                    count++;
                    //System.out.print("null value at");
                    //System.out.println(index);
                    int h1 = (int)Hash.djb2(key.toString(),size);
                    int h2 = (int)Hash.sdbm(key.toString(),size);
                    index = (h1 + count*h2)%size;

                }

            }
        }
        else return -98;
    }

    @Override
    public int delete(K key) {
        int count = 0;
        int index = (int)Hash.djb2(key.toString(), size);
        int firstindex = index;           //use it to break when element not found
        //System.out.println(index);
        //count++;
        if(this.contains(key)){
            while(true) {
                if(hashtable[index]!=  null) {
                    String full_name = hashtable[index].first_name+hashtable[index].second_name;
                    if(full_name.compareTo(key.toString())==0){
                        //return hashtable[index].student;
                        hashtable[index] = null;
                        return count+1;
                    }
                    else{
                        count++;
                        int h1 = (int)Hash.djb2(key.toString(),size);
                        int h2 = (int)Hash.sdbm(key.toString(),size);
                        index = (h1 + count*h2)%size;
                    }
                }
                else {
                    count++;
                    int h1 = (int)Hash.djb2(key.toString(),size);
                    int h2 = (int)Hash.sdbm(key.toString(),size);
                    index = (h1 + count*h2)%size;
                }
            }

        }
        else return -67;
    }

    @Override
    public boolean contains(K key) {
        // redefined using student must return concat of fname and lname..
        int index = (int)Hash.djb2(key.toString(),size);
        int findex = index;
        //System.out.println(index);
        int count =0;
        do {
            //System.out.println("came here!!");
            if(hashtable[index]!=  null) {
                if(!(hashtable[index].first_name+hashtable[index].second_name).equals(key.toString())){
                    count++;
                    //p = 12;
                    int h1 = (int)Hash.djb2(key.toString(),size);
                    int h2 = (int)Hash.sdbm(key.toString(),size);
                    index = (h1 + count*h2)%size;
                    //System.out.println("came here too");
                    //System.out.println(index);
                }
                else {
                   // count++;
                   // hashtable[index] = null;
                    //p = 12;
                   // System.out.println("came here too 2 ");
                    return true;
                }
            }
            else {
                count++;
                //p = 12;
                int h1 = (int)Hash.djb2(key.toString(),size);
                int h2 = (int)Hash.sdbm(key.toString(),size);
                index = (h1 + count*h2)%size;
                //System.out.println(index);
            }
        }
        while (index!=findex);

        return false;
    }

    @Override
    public T get(K key) throws NotFoundException {

            int index = (int) Hash.djb2(key.toString(),size);
            //int findex = index;
            int count = 0;
           if(this.contains(key)){
               while(true){
                   if(hashtable[index] !=null){
                       String full_name = hashtable[index].first_name+hashtable[index].second_name;
                       if(full_name.compareTo(key.toString())==0){
                           return hashtable[index].student;
                       }
                       else{
                           count++;
                           int h1 = (int)Hash.djb2(key.toString(),size);
                           int h2 = (int)Hash.sdbm(key.toString(),size);
                           index = (h1 + count*h2)%size;
                       }
                   }
                   else{
                       count++;
                       int h1 = (int)Hash.djb2(key.toString(),size);
                       int h2 = (int)Hash.sdbm(key.toString(),size);
                       index = (h1 + count*h2)%size;
                   }
               }
           }
           else return null;
    }

    @Override
    public String address(K key) throws NotFoundException {
        int index = (int) Hash.djb2(key.toString(), size);
        int count = 0;
        //String full_name;
        if (this.contains(key)) {
            while (true) {
                if (hashtable[index] != null) {
                    String full_name = hashtable[index].first_name+hashtable[index].second_name;
                    if(full_name.compareTo(key.toString())==0){
                        return Integer.toString(index);
                    }
                    else{
                        count++;
                        int h1 = (int)Hash.djb2(key.toString(),size);
                        int h2 = (int)Hash.sdbm(key.toString(),size);
                        index = (h1 + count*h2)%size;
                    }

                } else {
                    count++;
                    int h1 = (int)Hash.djb2(key.toString(),size);
                    int h2 = (int)Hash.sdbm(key.toString(),size);
                    index = (h1 + count*h2)%size;

                }
            }
        }
    else return "-2";
    }


}
