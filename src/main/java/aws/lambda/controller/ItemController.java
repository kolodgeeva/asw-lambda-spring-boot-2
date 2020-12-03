package aws.lambda.controller;

import static lombok.AccessLevel.PRIVATE;

import aws.lambda.domain.Item;
import aws.lambda.service.ItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
@RequestMapping(path = "/items")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ItemController {

  ItemService itemService;

  @GetMapping
  public List<Item> getItems() {
    return itemService.getItems();
  }

  @GetMapping(path = "/{name}")
  public Item getItem(@PathVariable String name) {
    return itemService.getItem(name);
  }

  @PostMapping
  public Item createItem(@RequestBody Item item) {
    return itemService.createItem(item);
  }

  @PutMapping("/{itemId}")
  public Item updateItem(@RequestBody Item item, @PathVariable String itemId) {
    return itemService.updateItem(item, itemId);
  }

  @DeleteMapping("/{itemId}")
  public void deleteEmployee(@PathVariable String itemId) {
    itemService.deleteItem(itemId);
  }

}
