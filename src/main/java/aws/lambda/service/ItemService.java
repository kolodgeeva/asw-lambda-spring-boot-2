package aws.lambda.service;

import static java.time.ZonedDateTime.now;

import aws.lambda.domain.Item;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

  public List<Item> getItems() {
    Item item1 = Item.builder()
        .id(UUID.randomUUID().toString())
        .name("name1")
        .date(now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
        .build();
    Item item2 = Item.builder()
        .id(UUID.randomUUID().toString())
        .name("name2")
        .date(now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
        .build();
    Item item3 = Item.builder()
        .id(UUID.randomUUID().toString())
        .name("name3")
        .date(now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
        .build();
    return List.of(item1, item2, item3);
  }

  public Item getItem(String name) {
    return Item.builder()
        .id(UUID.randomUUID().toString())
        .name(name)
        .date(now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
        .build();
  }

}
