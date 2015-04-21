package thegarlic.forum.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class BaseServiceImpl {
    public PageRequest createPageReauest(Integer pageNumber, Integer pageSize, Sort.Order order) {
        
        // spring data jpa page 는 index를 기준으로 하므로 무조건 1 빼줄 것
        pageNumber -= 1;

        //정렬순서는 기본적으로 sortOrder를 우선순위로 하되, id는 항상 역순으로 배치한다.
        Sort.Order[] orders = {
                order,
                new Sort.Order(Direction.DESC, "id")
        };

        Sort sort = new Sort(orders);

        return new PageRequest(pageNumber, pageSize, sort);
    }
}
