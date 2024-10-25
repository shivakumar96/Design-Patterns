package org.example.designpatterns.structural;

//hierarchical representation of object is composite design pattern. // similar to trie
//eg: FileSystem

import java.util.ArrayList;
import java.util.List;

interface FileSystem{
    void ls();
}

class Directory implements FileSystem{
    String dirName;
    Directory(String dirName){
        this.dirName = dirName;
    }
    List<FileSystem> files = new ArrayList<>();

    public void add(FileSystem file){
        files.add(file);
    }
    public void ls(){
        //prints current directory name;
        System.out.println(dirName);
        // ls on the other files
        for(FileSystem file : files){
            file.ls();
        }
    }
}

class File implements FileSystem{
    String fileName;

    File(String fileName){
        this.fileName = fileName;
    }

    public void ls(){
        System.out.println(fileName);
    }

}

public class Composite {
    public static void main(String[] args) {
        Directory root = new Directory("/");
        Directory sub = new Directory("dir1");
        sub.add(new File("file1"));
        root.add(sub);
        root.ls();
    }
}
