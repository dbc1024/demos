package com.ectrip.ec.noteBook.service.iservice;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.ec.model.notebook.Notebook;

 

public interface INotebookService extends IGenericService{

	void notebookSave(Notebook notebook);

	void delNotebooks(String[] noteids);

}
