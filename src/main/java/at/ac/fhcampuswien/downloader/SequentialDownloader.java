package at.ac.fhcampuswien.downloader;

import at.ac.fhcampuswien.controllers.NewsApiException;

import java.util.List;

// Class is needed for exercise 4 - ignore for exercise 3 solution
public class SequentialDownloader extends Downloader {

    // returns number of downloaded article urls
    @Override
    public int process(List<String> urls) {
        int count = 0;
        for (String url : urls) {
            try {
                String fileName = saveUrl2File(url);
                if(fileName != null)
                    count++;
            } catch (NewsApiException e){
                //System.err.println(e.getMessage());
                // throw new NewsApiException(e.getMessage());
            } catch (Exception e){
                System.out.println("Different problem occurred in " + this.getClass().getName() + ". Message: " + e.getMessage());
                //throw new NewsApiException("Different problem occurred in " + this.getClass().getName() + ". Message: " + e.getMessage());
            }
        }
        return count;
    }
}
