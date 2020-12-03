package aws.lambda.service;

import static lombok.AccessLevel.PRIVATE;

import aws.lambda.domain.Item;
import aws.lambda.domain.ItemRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ItemService {

  ItemRepository itemRepository;

  public List<Item> getItems() {
    return StreamSupport.stream(itemRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }

  public Item getItem(String name) {
    return itemRepository.findById(name).orElse(null);
  }

  public Item createItem(Item item) {
    return itemRepository.save(item);
  }

  public Item updateItem(Item item, String itemId) {
    Item dbItem = itemRepository.findById(itemId)
        .orElseThrow(() -> new IllegalStateException(
            String.format("Item with id %s doesn't exist", itemId)));

    Item updatedItem = dbItem.toBuilder()
        .name(item.getName())
        .date(item.getDate())
        .build();

    return itemRepository.save(updatedItem);
  }

  public void deleteItem(String itemId) {
    Item item = itemRepository.findById(itemId)
        .orElseThrow(() -> new IllegalStateException(
            String.format("Item with id %s doesn't exist", itemId)));
    itemRepository.delete(item);
  }

}
