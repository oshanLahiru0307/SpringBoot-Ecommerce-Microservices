package org.ecommerce.inventoryservice.Service;

import org.ecommerce.inventoryservice.DTO.InventoryRequestDTO;
import org.ecommerce.inventoryservice.DTO.InventoryResponseDTO;
import org.ecommerce.inventoryservice.Model.InventoryModel;
import org.ecommerce.inventoryservice.Repository.InventoryRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepo inventoryRepo;
    private final ModelMapper modelMapper;

    public InventoryService(InventoryRepo inventoryRepo, ModelMapper modelMapper) {
        this.inventoryRepo = inventoryRepo;
        this.modelMapper = modelMapper;
    }

    //get all inventory items
    public List<InventoryResponseDTO> getAllItems(){
        try{
            List<InventoryModel> items = inventoryRepo.findAll();
            return modelMapper.map(items, new TypeToken<List<InventoryResponseDTO>>(){}.getType());
        }catch(Exception e){
            System.out.println("Error while fetching items data" + e.getMessage());
            return null;
        }
    }

    //get inventory item by itemId
    public InventoryResponseDTO getItemById(Integer id){
        try{
            InventoryModel existingItem = inventoryRepo.findById(id).orElse(null);
            if(existingItem == null){
                System.out.println("Item with id " + id + " not found");
                return null;
            }
            return modelMapper.map(existingItem, InventoryResponseDTO.class);
        }catch(Exception e){
            System.out.println("Error while fetching items data" + e.getMessage());
            return null;
        }
    }

    //save new item
    public InventoryResponseDTO saveItem(InventoryRequestDTO item){
        try{
            InventoryModel newItem = modelMapper.map(item, InventoryModel.class);
            InventoryModel savedItem = inventoryRepo.save(newItem);
            return modelMapper.map(savedItem, InventoryResponseDTO.class);
        }catch(Exception e){
            System.out.println("Error while saving item data" + e.getMessage());
            return null;
        }
    }

    //update item by inventory id
    public InventoryResponseDTO updateItem(Integer id, InventoryRequestDTO item){
        try{
            InventoryModel existingItem = inventoryRepo.findById(id).orElse(null);
            if(existingItem == null){
                System.out.println("Item with id " + id + " not found");
                return null;
            }
            existingItem.setQuantity(item.getQuantity());
            existingItem.setProductId(item.getProductId());
            existingItem.setProductName(item.getProductName());
            inventoryRepo.save(existingItem);
            return modelMapper.map(existingItem, InventoryResponseDTO.class);
        }catch (Exception e){
            System.out.println("Error while updating item data" + e.getMessage());
            return null;
        }
    }

    //delete item by id
    public String deleteItem(Integer id){
        try{
            if(inventoryRepo.existsById(id)){
                inventoryRepo.deleteById(id);
                return "Item with id " + id + " deleted";
            }
            return "item not found with id" + id;
        }catch(Exception e){
            return "item not found with id" + id + "and error while deleting item" + e.getMessage();
        }
    }





}
