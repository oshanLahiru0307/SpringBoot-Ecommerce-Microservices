package org.ecommerce.inventoryservice.Controller;

import org.ecommerce.inventoryservice.DTO.InventoryRequestDTO;
import org.ecommerce.inventoryservice.DTO.InventoryResponseDTO;
import org.ecommerce.inventoryservice.Service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/inventory")
@CrossOrigin
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    //api endpoint for get all items
    @GetMapping("/getAllItems")
    public ResponseEntity<?> getAllItems() {
        List<InventoryResponseDTO> items = inventoryService.getAllItems();
        if(items == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(items);
    }

    //api endpoint for get item by its item
    @GetMapping("/{inventoryId}")
    public ResponseEntity<?> getItemById(@PathVariable Integer inventoryId) {
        InventoryResponseDTO item = inventoryService.getItemById(inventoryId);
        if(item == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(item);
    }

    //api endpoint for add item to the inventory
    @PostMapping("/addItem")
    public ResponseEntity<?> addItem(@RequestBody InventoryRequestDTO item){
        InventoryResponseDTO savedItem = inventoryService.saveItem(item);
        if(savedItem == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(savedItem);
    }

    //api endpoint for update inventory item data
    @PutMapping("/updateItem/{inventoryId}")
    public ResponseEntity<?> updateItem(@PathVariable Integer inventoryId, @RequestBody InventoryRequestDTO item){
        InventoryResponseDTO updatedItem = inventoryService.updateItem(inventoryId, item);
        if(updatedItem == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(updatedItem);
    }

    //api endpoint for delete inventory item
    @DeleteMapping("/deleteItem/{inventoryId}")
    public ResponseEntity<?> deleteItemById(@PathVariable Integer inventoryId) {
        String deleteItem = inventoryService.deleteItem(inventoryId);
        if(deleteItem == null || deleteItem.contains("not found")){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(deleteItem);
    }

}
