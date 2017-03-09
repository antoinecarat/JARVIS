package plugins.simpleBase;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import client.Event;
import client.IAgenda;
import client.IEvent;


public class AgendaFrame extends JFrame {
	
	private static final long serialVersionUID = 4211896515981463047L;
	
	private Map<String, JTextField> textFieldMap;
	
	private JButton create;
	private JTree displayEvents;
	private DefaultMutableTreeNode root;
	
	
	private IAgenda agenda;
	
	
	
	public AgendaFrame(IAgenda a){
		agenda = a;
		
		this.setTitle("Agenda JARVIS");
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.setLayout(new GridLayout(1, 2));
		
		
		
		//create the root node
        root = new DefaultMutableTreeNode("Evenement");
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        displayEvents = new JTree(treeModel);
        
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setLeafIcon(null);
        renderer.setClosedIcon(null);
        renderer.setOpenIcon(null);

        displayEvents.setCellRenderer(renderer);
        
		
		JPanel jp = new JPanel();
		
		// creation panel de gauche
		jp.setLayout(new GridLayout(10, 2));
		
		String label[] = {"name", "dateDebut", "dateFin", "mail", "tel", "frequence", "type", "description", "lieu"};

		this.textFieldMap = new HashMap<>();
		JTextField tf;
		for(int i=0; i < label.length; ++i){
			tf = new JTextField();
			
			jp.add(new Label(label[i]));
			jp.add(tf);
			
			this.textFieldMap.put(label[i], tf);
		}
		
		create = new JButton("creer");
		create.addActionListener(new AgendaFrameListener(this));
		jp.add(create);
		
		this.add(jp);
		
		// creation panel de droite
		jp = new JPanel();
		jp.setLayout(new GridLayout());
		jp.add(new JScrollPane(displayEvents));
		
		this.add(jp);
		
		
		this.setVisible(true);
	}
	
	
	public Map<String, String> getTextFieldValue(){
		Map<String, String> res = new HashMap<String, String>();
		for(String key: textFieldMap.keySet()){
			res.put(key, textFieldMap.get(key).getText());
		}
		return res;
	}
	
	public void addBranche(Map<String, String> arg){
//		creer un nouvel event
//		l'ajouter a root pour l'afichage
		
		
		IEvent e = new Event();
		
		
		//create the child nodes
		DefaultMutableTreeNode event = new DefaultMutableTreeNode(arg.get("name"));
        DefaultMutableTreeNode dateDeb = new DefaultMutableTreeNode("dateDeb : " + arg.get("dateDebut"));
        DefaultMutableTreeNode dateFin = new DefaultMutableTreeNode("DateFin : " + arg.get("dateFin"));
        DefaultMutableTreeNode mail = new DefaultMutableTreeNode("Mail : " + arg.get("mail"));
        DefaultMutableTreeNode tel = new DefaultMutableTreeNode("Tel : " + arg.get("tel"));
        DefaultMutableTreeNode freq = new DefaultMutableTreeNode("Frequence : " + arg.get("frequence"));
        DefaultMutableTreeNode type = new DefaultMutableTreeNode("Type : " + arg.get("type"));
        DefaultMutableTreeNode descr = new DefaultMutableTreeNode("Description : " + arg.get("description"));
        DefaultMutableTreeNode lieu = new DefaultMutableTreeNode("Lieu : " + arg.get("lieu"));
        
        event.add(dateDeb);
        event.add(dateFin);
        event.add(mail);
        event.add(tel);
        event.add(freq);
        event.add(type);
        event.add(descr);
        event.add(lieu);
        
        root.add(event);
        ((DefaultTreeModel)(displayEvents.getModel())).reload();
        
        agenda.addEvent(e);
	}
	
//	creer une methode ajoutant dans root un event
//	private void addToRoot(Ievent e)

}

