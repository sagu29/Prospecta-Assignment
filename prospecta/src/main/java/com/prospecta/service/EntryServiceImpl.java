package com.prospecta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospecta.entity.Entry;
import com.prospecta.repository.EntryRepository;

@Service
public class EntryServiceImpl implements EntryService{
	
	private EntryRepository er;
	
	
	@Autowired
	public EntryServiceImpl(EntryRepository er) {
		super();
		this.er = er;
	}



	@Override
	public Entry save(Entry entry) {
		// TODO Auto-generated method stub
		return er.save(entry);
	}

}
