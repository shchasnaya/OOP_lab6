package store;

import model.IWeight;

import java.io.Serializable;
import java.util.Arrays;

public class ProductStore implements Serializable {
    private IWeight[] arr = new IWeight[3];
    private int count = 0;

    //повертає копію масиву без пустих елементів
    public IWeight[] getArr() {
        return Arrays.copyOf(arr,count);
    }

    public void add(IWeight newProduct) {
        //запобігаємо переповненню масиву
        if(arr.length == count)
            arr = Arrays.copyOf(arr, count + count/2);
        //додаємоновий елемент
        arr[count++] = newProduct;

    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Каталог брусів:\n");
        for(int i = 0; i < count; i++) {
            sb.append(arr[i]).append("\n");
        }
        return sb.toString();
    }
}


