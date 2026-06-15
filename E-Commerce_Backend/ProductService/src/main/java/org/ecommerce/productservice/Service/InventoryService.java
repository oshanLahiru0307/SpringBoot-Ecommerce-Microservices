package org.ecommerce.productservice.Service;

import org.ecommerce.productservice.DTO.InventoryRequestDTO;
import org.ecommerce.productservice.DTO.InventoryResponseDTO;
import org.ecommerce.productservice.Model.InventoryEntity;
import org.ecommerce.productservice.Repository.InventoryRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepo inventoryRepository;
    private final ModelMapper modelMapper;

    public InventoryService(InventoryRepo inventoryRepository, ModelMapper modelMapper) {
        this.inventoryRepository = inventoryRepository;
        this.modelMapper = modelMapper;
    }

    //get all inventory items
    public List<InventoryResponseDTO> getAllInventoryItems() {
        List<InventoryEntity> inventoryEntities = inventoryRepository.findAll();
        if(inventoryEntities.isEmpty()) {
            return null;
        }
        return modelMapper.map(inventoryEntities, new TypeToken<List<InventoryResponseDTO>>(){}.getType());
    }

    //get inventory item by id
    public InventoryResponseDTO getInventoryItemById(int id) {
        InventoryEntity inventoryEntity = inventoryRepository.findById(id).orElse(null);
        if(inventoryEntity == null) {
            return null;
        }
        return modelMapper.map(inventoryEntity, InventoryResponseDTO.class);
    }

    //create inventory item
    public InventoryResponseDTO createInventoryItem(InventoryRequestDTO inventoryRequestDTO) {
        InventoryEntity inventoryEntity = modelMapper.map(inventoryRequestDTO, InventoryEntity.class);
        InventoryEntity savedInventoryEntity = inventoryRepository.save(inventoryEntity);
        return modelMapper.map(savedInventoryEntity, InventoryResponseDTO.class);
    }

    //update inventory item
    public InventoryResponseDTO updateInventoryItem(int id, InventoryRequestDTO inventoryRequestDTO) {
        InventoryEntity inventoryEntity = inventoryRepository.findById(id).orElse(null);
        if(inventoryEntity == null) {
            return null;
        }
        modelMapper.map(inventoryRequestDTO, inventoryEntity);
        InventoryEntity updatedInventoryEntity = inventoryRepository.save(inventoryEntity);
        return modelMapper.map(updatedInventoryEntity, InventoryResponseDTO.class);
    }

    //delete inventory item
    public String deleteInventoryItem(int id) {
        InventoryEntity inventoryEntity = inventoryRepository.findById(id).orElse(null);
        if(inventoryEntity == null) {
            return "Inventory item not found";
        }
        inventoryRepository.delete(inventoryEntity);
        return "Item Deleted Successfully";
    }
}
