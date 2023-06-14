package com.amis.misa.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ObjectConvert<T,G> {
	
	public ModelMapper modelMapper=new ModelMapper();
	
	private  Class<T> typeEntity;
	private  Class<G> typeDto;
	
	public ObjectConvert(Class<T> typeEntity,Class<G> typeDto){
		this.typeDto=typeDto;
		this.typeEntity=typeEntity;
		
	}
	
	public G convertToDto(T t) {
		return this.modelMapper.map(t,typeDto);
	}
	public T convertToEntity(G g) {
		return this.modelMapper.map(g,typeEntity);
	}
}
