package seg.crud.Auditoria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="sessao")
@Data
public class Sessao {
	@Id
	@Column( name="ip", length=12, nullable=false)
	private String ip;
	@Column( name="usuario", length=100, nullable = false)
	private String usuario;
}
