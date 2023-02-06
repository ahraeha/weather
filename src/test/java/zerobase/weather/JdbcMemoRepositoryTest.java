package zerobase.weather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;
import zerobase.weather.repository.JdbcMemoRepository;

@SpringBootTest
@Transactional
// 데이터 베이스 테스트를할때 많이 사용하는 어노테이션이다.
// 데이터 베이스 테스트할때 이 작업으로 인해 기존 데이터가 바뀌면 안되니까 그걸 위한 어노테이션이다.
public class JdbcMemoRepositoryTest {

  @Autowired
  JdbcMemoRepository jdbcMemoRepository;

  @Test
  void insertMemoTest () {
    //given(주어진것)
    Memo newMemo = new Memo(1, "this is new memo~");
    //when(무엇무엇을했을때)
    jdbcMemoRepository.save(newMemo);
    //then(결과는 이럴껏이다)
    Optional<Memo> result = jdbcMemoRepository.findById(1);
    assertEquals(result.get().getText(), "this is new memo~");
  }

  @Test
  void findAllMemoTest() {
      //given
      //when
    List<Memo> memoList = jdbcMemoRepository.findAll();
    System.out.println(memoList);
    //then
    assertNotNull(memoList);
  }

}
