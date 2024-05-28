package com.example.demo.Controller;

import com.example.demo.Dto.FoodDto;
import com.example.demo.Dto.FoodFindDto;
import com.example.demo.util.OpenApiManager;
import com.example.demo.Repository.Food.FoodRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {
    private final OpenApiManager openApiManager;
    private final FoodRepository foodRepository;

    @GetMapping("/openApi")
    public void setUp() throws ParseException, JsonProcessingException {
        int start = 1000;
        for(int i = 1;i<=10;i++){
            openApiManager.fetch(start,start+1000);
            start += 1000;
        }
    }
    @GetMapping("/open")
    public void op() throws URISyntaxException, JsonProcessingException {
        long after = System.currentTimeMillis();
        String baseUrl = "https://data.myhome.go.kr/rentalHouseList?serviceKey=0OhBU7ZCGIobDVKDeBJDpmDRqK3IRNF6jlf%2FJB2diFAf%2FfR2czYO9A4UTGcsOwppV6W2HVUeho%2FFPwXoL6DwqA%3D%3D&brtcCode=11&signguCode=140&numOfRows=168&pageNo=1";
        URI uri = new URI(baseUrl);
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(uri, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = objectMapper.readTree(jsonString);
        JsonNode hsmpList = json.get("hsmpList");
        if(hsmpList.isArray()){
            JsonNode[] rowArray = new JsonNode[hsmpList.size()];
            for (int i = 0; i < hsmpList.size(); i++) {
                rowArray[i] = hsmpList.get(i).get("hsmpSn");
                rowArray[i] = hsmpList.get(i).get("insttNm");
                rowArray[i] = hsmpList.get(i).get("brtcNm");
                rowArray[i] = hsmpList.get(i).get("signguNm");
                rowArray[i] = hsmpList.get(i).get("competDe");
                rowArray[i] = hsmpList.get(i).get("hshldCo");
                rowArray[i] = hsmpList.get(i).get("suplyTyNm");
                rowArray[i] = hsmpList.get(i).get("styleNm");
                rowArray[i] = hsmpList.get(i).get("insttNm");
                rowArray[i] = hsmpList.get(i).get("suplyPrvuseAr");
                rowArray[i] = hsmpList.get(i).get("suplyCmnuseAr");
                rowArray[i] = hsmpList.get(i).get("elvtrInstlAtNm");
                rowArray[i] = hsmpList.get(i).get("parkngCo");
                rowArray[i] = hsmpList.get(i).get("bassMtRntchrg");
                rowArray[i] = hsmpList.get(i).get("bassCnvrsGtnLmt");
            }
            for (JsonNode jsonNode : rowArray) {
                //System.out.println(jsonNode);
            }
        }
        long sec = System.currentTimeMillis();
        System.out.println(sec-after);
//        RestTemplate restTemplate = new RestTemplate();
//        ObjectMapper objectMapper = new ObjectMapper();
        //String baseUrl ="http://apis.data.go.kr/B552555/lhLeaseNoticeInfo1/lhLeaseNoticeInfo1?serviceKey=MunSbKW5rD1vmX1nW8NM9H1pYeJ3fTNtqDmkWYrGkSzTx30Q5GPFuiXzaBqGEUeY6majiUWbeaGEZcTEqrirYw%3D%3D&PG_SZ=10&PAGE=1";
        //URI uri = new URI(baseUrl);
//        String jsonString = restTemplate.getForObject(uri, String.class);
//        JsonNode json = objectMapper.readTree(jsonString);
//        JsonNode dsList = json.get(1).get("dsList");
//        if(dsList.isArray()){
//            JsonNode[] rowArray = new JsonNode[dsList.size()];
//            for (int i = 0; i < dsList.size(); i++) {
//                rowArray[i] = dsList.get(i);
//            }
//            for (JsonNode jsonNode : rowArray) {
//                System.out.println(jsonNode);
//            }
//        }
        //크롤링
    }
    @PostMapping("/find")
    public List<FoodDto> fetch(@RequestBody FoodFindDto foodFindDto) throws IOException, ParseException {
        List<String> name = foodFindDto.getNames();
        int page = foodFindDto.getPage();
        List<FoodDto> right = foodRepository.ingredient_not_included(name).stream().map(f -> {
            return new FoodDto(f.getName());
        }).toList();
        int p;
        if(page!=0) p = page*10;
        else p = 0;
        List<FoodDto> list = right.stream().skip(p).limit(p+10).toList();
        return list;
    }//사용자가 체크한 검색창에 체크한 것들을 뺴고 검색
}
