package at.ac.fhcampuswien.downloader;

import at.ac.fhcampuswien.controllers.NewsApiException;
import okhttp3.Call;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// Class is needed for exercise 4 - ignore for exercise 3 solution
public class ParallelDownloader extends Downloader{

    int count;
    // returns number of downloaded article urls
    @Override
    public int process(List<String> urls) throws NewsApiException, ExecutionException, InterruptedException {

        // TODO implement download function using multiple threads
        // Hint: use ExecutorService with Callables
        count = 0;
        int threadNumbers = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(threadNumbers);


        List<Callable<String>> callables = new ArrayList<>();
        for(String url : urls){
            Callable<String> task = () -> {
                try{
                    return saveUrl2File(url);
                }
                catch (Exception e){
                    return "Your cat couldn't download url: " + e.getMessage();
                }
            };
            callables.add(task);
        }
        try{
            List<Future<String>> allFutures = executorService.invokeAll(callables);
            for(Future<String> cat : allFutures){
                    if(!cat.get().contains("Your cat couldn't download url")){
                        count++;
                    }
                    String result = cat.get();
                    //System.out.println(result);
                }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            throw new NewsApiException("Failed to download.");
        }
        return count;
    }
}
