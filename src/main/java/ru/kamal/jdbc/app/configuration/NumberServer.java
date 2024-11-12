package ru.kamal.jdbc.app.configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;

public class NumberServer {
    URLConnection connection = null;
    InputStream response = null;
    BufferedReader in;
    Set<String> set;
    Boolean overlap, overlapOne;
    Integer sizeSet;
    String number_order;
    public NumberServer()
    {
        set = new HashSet<String>();
    }

    public String NumberServer() throws IOException {
        Integer i=1;
        while (i!=3) {
            switch (i) {
                case 1 :
                {
                    number_order = GetNumber();
                    i++;
                }
                case 2 :
                {
                    overlapOne = TestNumber(number_order);
                    i++;
                }
                case 3 :
                {
                    if (overlapOne == false)
                    {
                        break;
                    } else
                    {
                        i = 1;
                    }
                }
            }
        }
        return number_order;
    }
    public Boolean TestNumber (String number) {
    sizeSet = set.size();
    set.add(number);
        if (sizeSet!=set.size()) {
            overlap = false;
        }
        else
        {
            overlap = true;
        }
        return overlap;
    }
    public String GetNumber () throws IOException {
        connection = new URL("http://localhost:8090/number").openConnection();
        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine = in.readLine();
        in.close();
        return inputLine;
    }

}
