package com.newspundit.newslibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NewsController {

    // MP - zawsze i wszędzie pola prywatne (przynajmniej póki nie zrozumiesz kiedy nie muszą)
    /* MP - to jest poprawnie, ale dobrze nawyki sobie robić, że prawie zawsze są 3 warstwy:
    - controller - on określa jak zachowuje się API i z grubsza niech się tylko tym zajmuje
    - service - warstwa biznesowa; tu cała logika jak ma się zachowywać aplikacja powinna być
    - repozytoria - warstwa dostępu do danych
    Jak chcesz żeby coś się zadziało (zmieniło, policzyło ...) to warto to przez service robić, a nie w kontrolerze.
    W prostych projektach nie widać sensu, ale wytłumaczę ci F2F czemu tak.
    * */
    @Autowired
    NewsRepository newsRepository;

    @GetMapping("/test")
    public int test(){
        return 1;
    }

    @GetMapping("/sites")
    public List<String> getSites() {
        return newsRepository.getSites();
    }
    // MP - brakuje entera
    @GetMapping("/news/{id}")
    public News getById(@PathVariable("id") int id){
        return newsRepository.getById(id);
    }

    @PostMapping("/news")
    // MP - piękna nazwa metody :D
    public int ass(@RequestBody List<News> news){
        // MP - co jak przyjdzie null? pusta lista? lista z null'ami? Może tak być czy nie?
        /* MP - ogólnie zazwyczaj w REST wszelkie operacje wstawiające zwracają obiekty wstawione. Tu tego nie masz ale
        często ID wstawianego obiektu jest generowane (nie przychodzi w JSON) i wtedy fajnie jakby frontend wiedział pod
        jakim ID się wstawiły te rzeczy.
        */
        return newsRepository.save(news);
    }

    // MP - ogólnie PATCH jest poprawny ale nigdy w projekcie go nie używaliśmy
    @PatchMapping("/news/{id}")
    public int update(@PathVariable("id") int id, @RequestBody News updatedNews){
        News news = newsRepository.getById((id));
        if (news != null){
            // MP - no i właśnie ta część nadaje się do serwisu
            if (updatedNews.getAddress() != null) news.setAddress(updatedNews.getAddress());
            if (updatedNews.getSite() != null) news.setSite(updatedNews.getSite());
            if (updatedNews.getAuthor() != null) news.setAuthor(updatedNews.getAuthor());
            if (updatedNews.getTitle() != null) news.setTitle(updatedNews.getTitle());

            newsRepository.update(news);

            return 1;
        } else {
            return -1;
        }
    }

    @DeleteMapping("/news/{id}")
    public int delete(@PathVariable("id") int id){
        return newsRepository.delete(id);
    }
}
