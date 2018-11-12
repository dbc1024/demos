package com.ectrip.ec.noteBook.dao;

import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ec.model.notebook.Notebook;
import com.ectrip.ec.noteBook.dao.idao.INotebookDAO;

public class NotebookDAO extends GenericDao implements INotebookDAO {
	/**
	 * ��������
	 */
	public void notebookSave(Notebook notebook) {
		notebook.setNoteid(getMaxNoteid() + 1);
		this.save(notebook);
	}

	/**
	 * ��ȡ����Noteid
	 * 
	 * @return noteid
	 */
	public long getMaxNoteid() {
		long noteid = 0;
		String sql = "select max(n.noteid) from Notebook n";
		List list = this.find(sql);
		if (list.size() > 0) {
			if (list.get(0) == null) {
				noteid = 0;
			} else {
				noteid = Long.parseLong(list.get(0).toString() == null ? "0"
						: list.get(0).toString());
			}
		}
		return noteid;
	}

	public void delNotebooks(String[] noteids) {
		Notebook notebook = null;
		 for(int i=0;i<noteids.length;i++){
			notebook = (Notebook)this.get(Notebook.class, Long.valueOf(noteids[i]));
			this.delete(notebook);
		 }
		
	}
}
