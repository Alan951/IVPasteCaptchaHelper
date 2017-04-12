/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ivpastecaptchahelper;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import org.nibor.autolink.LinkExtractor;
import org.nibor.autolink.LinkSpan;
import org.nibor.autolink.LinkType;

/**
 *
 * @author Ck
 */
public class Utils {
    
    public static String parseUrl(String url){        
        return url.replaceAll("\\/v\\/", "/p/");
    }
    
    public static String[] extractUrls(String text){
        List<String> linksList = new ArrayList<>();
        
        LinkExtractor linkExtractor = LinkExtractor.builder()
                .linkTypes(EnumSet.of(LinkType.URL, LinkType.WWW))
                .build();
        
        Iterable<LinkSpan> links = linkExtractor.extractLinks(text);
        
        Iterator<LinkSpan> linksIterator = links.iterator();
        
        String link;
        while(linksIterator.hasNext()){
            LinkSpan lispan = linksIterator.next();
            
            link = text.substring(lispan.getBeginIndex(), lispan.getEndIndex());
            
            if(link.startsWith("www")){
                link = "http://"+link;
            }
            
            linksList.add(link);
        }
        
        linksList.forEach((linkElement) -> {
            System.out.println("Link found: " + linkElement + " | " +parseUrl(linkElement));
        });
        
        return linksList.toArray(new String[linksList.size()]);
    }
}
