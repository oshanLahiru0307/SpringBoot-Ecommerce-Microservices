package org.ecommerce.productservice.Controller;

import org.ecommerce.productservice.DTO.InventoryRequestDTO;
import org.ecommerce.productservice.DTO.InventoryResponseDTO;
import org.ecommerce.productservice.Service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    //get all inventory items
    @GetMapping
    public ResponseEntity<?> getAllInventoryItems() {
        List<InventoryResponseDTO> items = inventoryService.getAllInventoryItems();
        if(items.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(items);
    }

    //get inventory item by id
    @GetMapping("/product/{itemId}")
    public ResponseEntity<?> getInventoryItemById(@PathVariable Integer itemId) {
        InventoryResponseDTO item = inventoryService.getInventoryItemById(itemId);
        if(item == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(item);
    }

    //create inventory item
    @PostMapping("/create")
    public ResponseEntity<?> createInventoryItem(@RequestBody InventoryRequestDTO inventoryRequestDTODTO) {
        InventoryResponseDTO createdItem = inventoryService.createInventoryItem(inventoryRequestDTODTO);
        return ResponseEntity.ok().body(createdItem);
    }

    //update inventory item
    @PutMapping("/update/{itemId}")
    public ResponseEntity<?> updateInventoryItem(@PathVariable Integer itemId, @RequestBody InventoryRequestDTO inventoryRequestDTODTO) {
        InventoryResponseDTO updatedItem = inventoryService.updateInventoryItem(itemId, inventoryRequestDTODTO);
        if(updatedItem == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(updatedItem);
    }

    //delete inventory item
    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<?> deleteInventoryItem(@PathVariable Integer itemId) {
        String deleted = inventoryService.deleteInventoryItem(itemId);
        if(deleted.contains("not found")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body("Inventory item deleted successfully");
    }

}
