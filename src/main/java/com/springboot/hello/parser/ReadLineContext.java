package com.springboot.hello.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadLineContext<T> {

    private Parser<T> parser;

    public ReadLineContext(Parser<T> parser) {
        this.parser = parser;
    }

    public List<T> readByLine(String filename) throws IOException {
        List<T> result = new ArrayList<>(); //결과를 담을 ArrayList
        BufferedReader reader = new BufferedReader(new FileReader(filename));   //File 읽어옴
        String str;
        reader.readLine();  //첫 줄은 저장하지 않도록 처리
        while ((str = reader.readLine()) != null) { //한 줄씩 읽어 result에 저장
            try {
                result.add(parser.parse(str));
            } catch (Exception e){
                System.out.printf("파싱중 문제가 생겨 이라인은 넘어갑니다. 파일내용:%s",str);
            }

        }
        reader.close();
        return result;
    }
}

