public class SCBST<K extends Comparable<T>, T> implements MyHashTable_<K, T> {
    int size;
    //int inserted_elements;
    Node<T>[] studarr;
   // Node<T> root;
    //int node_touched;
    SCBST(int arrsize){

        size = arrsize;
        studarr = new Node[size];
       // inserted_elements = 0;

    }

    @Override
    public int insert(K key, T obj) {
        int node_touched = 0;
        int index = (int) (Hash.djb2(key.toString(),size));

        Node<T> node = new Node<T>(obj);
        node.first_name = node.student.toString();
        node.second_name = key.toString().substring(node.first_name.length());
        if(studarr[index]!=null && !this.contains(key)){
            Node<T> root = studarr[index];
            Node<T> curr;
            Node<T> prev;
            curr = root;
            prev = null;
            while(curr!= null){
                node_touched++;
                if(node.student.toString().compareTo(curr.student.toString())>=0){
                    prev = curr;
                    curr = curr.right;
                }
                else{
                        prev = curr;
                        curr = curr.left;
                }
            }

            assert prev != null;
            if(prev.student.toString().compareTo(node.student.toString())>0 ){

                String stu = node.student.toString();
                node.first_name = stu;
                node.second_name = key.toString().substring(stu.length());
                prev.left = node;

            }

            else{
                String stu = node.student.toString();
                node.first_name = stu;
                node.second_name = key.toString().substring(stu.length());
                prev.right = node;
            }
            return (node_touched+1);

        }
        else if(studarr[index]==null && !this.contains(key)){
            String stu = node.student.toString();
            node.first_name = stu;
            node.second_name = key.toString().substring(stu.length());  // can second name be an empty string??? NO
            studarr[index] = node;
            return 1;
        }
        else return -1;

    }

    @Override
    public int update(K key, T obj) {
        int counter=0;
        int index = (int)Hash.djb2(key.toString(),size);
        Node<T> prev = null;

        if(studarr[index] != null && this.contains(key)) {
            Node<T> curr = studarr[index];
            counter++;
            Node<T> updated = new Node<T>(obj);
            updated.first_name = obj.toString();
            updated.second_name = key.toString().substring(obj.toString().length());


            while (true) {
                //assuming node will be found..
                if (curr.student.toString().compareTo(updated.student.toString()) > 0) {
                    prev = curr;
                    curr = curr.left;
                    counter++;
                } else if (curr.student.toString().compareTo(updated.student.toString()) < 0) {
                    prev = curr;
                    curr = curr.right;
                    counter++;
                } else {
                    if (curr.second_name.compareTo(updated.second_name) == 0) {
                        curr.student = updated.student;
                        return counter;
                    } else {
                        counter++;
                        prev = curr;
                        curr = curr.right;
                    }
                }
            }
          //  return -67;          //node not found
        }
        else return -9;          //use of sentinel to signify that node is not found.

    }

    public int delete(K key){
        int counter=0;
        if(this.contains(key)){
            int index =(int) Hash.djb2(key.toString(), size);
            Node<T> curr = studarr[index];
            Node<T> prev = null;
            counter++;
            while(true){
                if(key.compareTo(curr.student)<0){
                    prev = curr;
                    curr = curr.left;
                    counter++;
                }
                else if(key.compareTo(curr.student)>0){
                    counter++;
                    prev = curr;
                    curr = curr.right;
                }
                else{
                    if(key.toString().compareTo(curr.first_name+curr.second_name)!=0){
                        counter++;
                        prev = curr;
                        curr = curr.right;
                    }
                    else{

                        if(curr.left == null && curr.right == null && prev!=null){
                            //System.out.println("NO way");
                            if(curr == prev.left){
                                prev.left = null;
                                return counter;
                            }
                            else{
                                prev.right = null;
                                return counter;
                            }
                        }
                        else if(curr.left== null && curr.right == null && prev==null){
                            studarr[index] = null;
                            return counter;
                        }
                        else if(curr.left!=null && curr.right==null && prev!= null){
                            //System.out.println("came");
                            if(curr == prev.left){
                                prev.left = curr.left;
                                return counter+1;
                            }
                            else{
                                prev.right = curr.left;
                                return counter+1;
                            }
                        }
                        else if(curr.left!=null && curr.right==null && prev==null){
                            studarr[index] = curr.left;
                            return counter+1;
                        }
                        else if(curr.left== null && curr.right!=null && prev!=null){
                            if(curr == prev.left){
                                prev.left = curr.right;
                                return counter+1;
                            }
                            else{
                                prev.right = curr.right;
                                return counter+1;
                            }
                        }
                        else if(curr.left==null && curr.right!=null && prev==null){
                            this.studarr[index] = curr.right;
                            return counter+1;
                        }
                        else if(curr.left!= null && curr.right!=null && prev!= null){
                            //System.out.println("comeagain!!");
                            //System.out.println(counter);
                            Node<T> stag_prev = prev;
                            Node<T> stag = curr;
                            prev = curr;
                            curr = curr.right;
                            counter++;
                            while(curr.left!=null){
                                counter++;
                                prev = curr;
                                curr = curr.left;
                            }

                            if(curr == prev.right){
                                curr.left = stag.left;
                                //counter++;
                                if(stag == stag_prev.right){
                                    stag_prev.right = curr;
                                }
                                else{
                                    stag_prev.left = curr;
                                }
                                return counter;
                            }
                            else{
                              //  System.out.println("came here");
                                if(curr.right!=null){
                                   prev.left = curr.right;
                                   curr.left = stag.left;
                                   curr.right = stag.right;
                                   if(stag == stag_prev.right){
                                       stag_prev.right = curr;
                                   }
                                   else stag_prev.left = curr;
                                   return counter+1;
                                }
                                else{
                                    prev.left = null;
                                    curr.left = stag.left;
                                    curr.right = stag.right;
                                    if(stag == stag_prev.right){
                                        stag_prev.right  =curr;
                                    }
                                    else {
                                        stag_prev.left = curr;
                                    }
                                    return counter;
                                }


                            }
                          //  return counter+1;
                        }
                        else if(curr.left!=null &&curr.right!=null && prev==null){
                            //int counter =0;
                            Node<T> stag = curr;
                            prev = curr;
                            curr = curr.right;
                            counter+= 1;
                            while(curr.left!= null){
                                counter++;
                                prev = curr;
                                curr = curr.left;
                            }
                            if(curr==prev.right){
                                    curr.left = stag.left;
                                    studarr[index] = curr;
                                    return counter;

                            }
                            else{
                                if(curr.right!=null){
                                    prev.left = curr.right;
                                    curr.left = stag.left;
                                    curr.right= stag.right;
                                    studarr[index] = curr;
                                    return counter+1;
                                }
                                else{
                                    prev.left = null;
                                    curr.left = stag.left;
                                    curr.right = stag.right;
                                    studarr[index] = curr;
                                    return counter;
                                }

                            }
                        }
                    }
                }
            }

        }
        else return -1;
    }

    @Override
    public boolean contains(K key) {
        int index = (int)Hash.djb2(key.toString(),size);
        Node<T> curr = this.studarr[index];
        //Node<T> prev = null;
        while(curr!=null){
            if(key.compareTo(curr.student)<0 && curr.left!= null){
               // prev = curr;
                curr = curr.left;

            }
            else if(key.compareTo(curr.student)<0 && curr.left== null){
                return false;
            }
            else if(key.compareTo(curr.student)>0 && curr.right!= null){
                //prev = curr;
                curr = curr.right;
            }
            else if(key.compareTo(curr.student)>0 && curr.right== null){
                return false;
            }
            else if(key.compareTo( curr.student)==0){
                if(key.toString().compareTo(curr.first_name+curr.second_name)==0){
                    return true;
                }
                else{
                    if(curr.right==null){
                        return false;
                    }
                    else curr = curr.right;
                }
            }
        }
        return false;
    }

    @Override
    public T get(K key) throws NotFoundException {
        if(this.contains(key)){
            int index = (int)Hash.djb2(key.toString(),size);
            Node<T> curr= studarr[index];
           // Node<T> prev = null;
            while(true){
                if(key.compareTo(curr.student)<0){
                   // prev = curr;
                    curr = curr.left;
                }
                else if(key.compareTo(curr.student)>0){
                   // prev = curr;
                    curr = curr.right;
                }
                else{
                    if(key.toString().compareTo(curr.first_name+curr.second_name)==0){
                        return curr.student;
                    }
                    else{
                        curr = curr.right;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String address(K key) throws NotFoundException {
        String output = "";
        if(this.contains(key)){
            int index =(int) Hash.djb2(key.toString(),size);

            output += index;
            output += "-";
            Node<T> curr = studarr[index];
            Node<T> prev = null;
            while(true){
                if(key.compareTo(curr.student)<0){
                   curr = curr.left;
                   output += "L";
                }
                else if(key.compareTo(curr.student)>0){
                   // prev = curr;
                    curr = curr.right;
                    output += "R";
                }
                else{
                    if(key.toString().compareTo(curr.first_name+curr.second_name)==0){
                        break;
                    }
                    else{
                        curr = curr.right;
                        output += "R";
                    }

                }
            }
            return output;

        }
        else return null;
    }
}
