package Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class ResponseParser {
    public void parse() throws IOException, InterruptedException {
        CSVWriter writer = new CSVWriter();
        writer.open();
        Document doc = Jsoup.parse(new RequestSender().sendRequest());

        Element thead = doc.getElementsByTag("thead").get(0);
        Element tbody = doc.getElementsByTag("tbody").get(0);

        String[] row = new String[thead.child(0).children().size()];
        for (int i=0; i < row.length; i++){
            row[i] = thead.child(0).child(i).ownText();
        }
        writer.writeLine(row);

        for (Element tr: tbody.children()){
            for (int i=0; i < tr.children().size(); i++){
                row[i] = tr.child(i).ownText();
            }
            writer.writeLine(row);
        }

        writer.close();
    }

    public static void main(String[] args) {
        ResponseParser parser = new ResponseParser();
        try {
            parser.parse();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
