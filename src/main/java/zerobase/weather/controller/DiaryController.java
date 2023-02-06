package zerobase.weather.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

@RestController // 클라이언트와 가까이 있음//RestController 상태코드를 컨트롤러를 지정해서 내려줄수잇겟끔하는것
public class DiaryController {

  private final DiaryService diaryService;

  //생성자 생성
  public DiaryController(DiaryService diaryService) {
    this.diaryService = diaryService;
  }

  //api 경로
  //parameter와 requestbody로 받음
  //Body에 일기값인 Text에 넣겟다.
  @ApiOperation(value = "다이어리 생성", notes = "날짜 입력 및 글 내용 입력하기")
  @PostMapping("/create/diary")
//저장할경우 post
  void createDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  LocalDate date, @RequestBody String text) {
    diaryService.createDiary(date, text);
  }

  @ApiOperation("다이어리 읽기 (날짜 지정 검색)")
  @GetMapping("/read/diary")
  List<Diary> readDiary(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
    return diaryService.readDiary(date);
  }

  @ApiOperation("다이어리 읽기 (날짜 범위 검색)")
  @GetMapping("/read/diaries")
  List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso = ISO.DATE)
  @ApiParam(value = "조회할 기간의 첫번째날", example = "2020-02-02") LocalDate startDate,
      @RequestParam @DateTimeFormat(iso = ISO.DATE)
      @ApiParam(value = "조회할 기간의 마지막날", example = "2020-02-02") LocalDate endDate) {
    return diaryService.readDiaries(startDate, endDate);
  }

  @ApiOperation("다이어리 수정하기")
  @PutMapping("/update/diary")
  void updateDiary(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date,
      @RequestBody String text) {
    diaryService.updateDiary(date, text);
  }

  @ApiOperation("다이어리 삭제하기 (날짜 지정)")
  @DeleteMapping("delete/diary")
  void deleteDiary(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
    diaryService.deleteDiary(date);
  }
}
