package com.example.a4mt19cs171_parse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class viewdataactivity extends AppCompatActivity {
    TextView xmlcontenttextview,jsoncontenttextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdataactivity);
        xmlcontenttextview=(TextView)findViewById(R.id.view_xml_content_id);
        jsoncontenttextview=(TextView)findViewById(R.id.view_json_content_id);
        Intent intent=getIntent();
        String datatype=intent.getStringExtra("datatype");
        if(datatype.equals("xml"))
        {
          //  xmlcontenttextview.setText("Test xml parsed content");
            xmlcontenttextview.setText("");
            try {
                InputStream is = getAssets().open("weather.xml");

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(is);

                Element element = (Element) doc.getDocumentElement();
                element.normalize();

                NodeList nList = doc.getElementsByTagName("weather");

                for (int i = 0; i < nList.getLength(); i++) {

                    Node node = nList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element2 = (Element) node;
                        xmlcontenttextview.setText(xmlcontenttextview.getText() + "\nCity Name : " + getValue("City_Name", element2) + "\n");
                        xmlcontenttextview.setText(xmlcontenttextview.getText() + "Latitude : " + getValue("Latitude", element2) + "\n");
                        xmlcontenttextview.setText(xmlcontenttextview.getText() + "Longitude : " + getValue("Longitude", element2) + "\n");
                        xmlcontenttextview.setText(xmlcontenttextview.getText() + "Temperature : " + getValue("Temperature", element2) + "\n");
                        xmlcontenttextview.setText(xmlcontenttextview.getText() + "Humidity : " + getValue("Humidity", element2) + "\n");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else if(datatype.equals("json"))
        {
            jsoncontenttextview.setText("Test json parsed content");{
            try {
                InputStream inputStream = getAssets().open("weather.json");
                {
                    byte[] data = new byte[inputStream.available()];
                    inputStream.read(data);
                    String jsonString = new String(data);
                    JSONObject jsonobject = new JSONObject(jsonString);
                    JSONObject weather = jsonobject.getJSONObject("weather");
                    jsoncontenttextview.setText("city_name:" + weather.getString("city_name") +"\n");
                    jsoncontenttextview.append("Latitude:" + weather.getString("Latitude") +"\n");
                    jsoncontenttextview.append("Longitude:" + weather.getString("Longitude") +"\n");
                    jsoncontenttextview.append("temperature:" + weather.getString("temperature") +"\n");
                }
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }

        }

        }
    }

    private String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
