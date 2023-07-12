package com.newspundit.newslibrary.service;

import com.newspundit.newslibrary.dto.NewsDto;
import com.newspundit.newslibrary.mapper.NewsMapper;
import com.newspundit.newslibrary.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
//import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.newspundit.newslibrary.model.News;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    public News getNewsById(Long id) {
        /**
         * p00h - getReference to specyficzna metoda i tu na pewno nie pasuje. Tu powinieneś użyć findById.
         * Nie mam bazy żeby sprawdzić czy u Ciebie aplikacja loguje SQLe, które robi. Jak nie to dodaj sobie bo to
         * bardzo przydatne:
         * https://www.baeldung.com/sql-logging-spring-boot
         * Prawdopodobnie przez to, że dałeś getReference będziesz miał 2 razy SELECT robiony. To trochę bardziej
         * skomplikowana sprawa na początek, więc moim zdaniem bezpieczniej jest robić findById zawsze, póki nie
         * zrozumiesz różnicy.
         */
        return newsRepository.getReferenceById(id);
                //.orElseThrow(() -> new NewsNotFoundException("News not found with ID: " + id));
    }

    public News addNews(NewsDto newsDto) {

        News news = NewsMapper.toEntity(newsDto);

        return newsRepository.save(news);
    }

    public News updateNews(Long id, NewsDto newsDto) {
        //it needs all fields in JSON file (not provided => null in database)

        News existingNews = newsRepository.getReferenceById(id);
        //throw exception if no id

        try {
            BeanUtils.copyProperties(existingNews, newsDto);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update news", e);
        }

        return newsRepository.save(existingNews);
    }

    public News updateNewsNotNull(Long id, NewsDto newsDto) {
        //it doesn't need all fields in JSON file (not provided => no change)

        /**
         * p00h - to jest poprawnie, ale uważaj z tym getReferenceById.
         */
        News existingNews = newsRepository.getReferenceById(id);

        try {
            java.util.Map<String, String> propertyMap = BeanUtils.describe(newsDto);
            for (Map.Entry<String, String> entry : propertyMap.entrySet()) {
                String propertyName = entry.getKey();
                String propertyValue = entry.getValue();
                if (propertyValue != null) {
                    BeanUtils.setProperty(existingNews, propertyName, propertyValue);
                }
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace(); // Handle or throw an exception as needed
            /**
             * p00h - no to moim zdaniem nie jest dobra praktyka. Nie znam szczegółów kiedy exceptiony mogą polecieć, ale:
             * 1. Nie wiesz ile zostanie zrobione a ile nie - jak sypnie się dla pierwszego pola to nic nie zostanie zrobione.
             * Jak sypnie się dla drugiego to tylko pierwsze pole zmienisz a pozostałe nie. Itd.
             * Może się więc zdarzyć, że zmienisz obiekt tylko częściowo i wprowadzisz go w niepoprawny stan.
             * 2. Same zalogowanie w tym momencie ukryje problem, bo z perspektywy użytkownika będzie wszystko ok - bo
             * coś zapisało się. A nikt nie siedzi i nie śledzi czy coś w logach jest.
             *
             * Moim zdaniem najlepiej jest rzucić tu RuntimeException. Wtedy nie musisz ich deklarować w metodzie, a i
             * nie będzie udawać że wszystko jest ok. Ja wolałbym, żeby nic się nei zapisało niż zapisało tylko "losowe"
             * pola.
             */

        }
        return newsRepository.save(existingNews);
    }
}


