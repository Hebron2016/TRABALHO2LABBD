DROP DATABASE AuditoriaJPA 
GO
USE AuditoriaJPA 

CREATE TRIGGER t_ipvalido ON sessao
AFTER INSERT, UPDATE
AS
BEGIN
	DECLARE @vldIp VARCHAR(12),
			@numIp BIGINT,
			@vldNum BIT
	SET @vldIp = (SELECT ip FROM inserted)
	IF(LEN(@vldIp) > 12 OR LEN(@vldIp)<4)
	BEGIN
		ROLLBACK
		RAISERROR('Não existe IP maior que 12 ou menor 4 digitos',16,2)
	END
	ELSE 
	BEGIN 
		SET @vldNum = (ISNUMERIC(@vldIp))
		IF(@vldNum = 1)	
		BEGIN
			SET @numIp = CAST(@vldIp AS bigint)
			IF( @numIp > 255255255255 OR @numIp < 1000 )
			BEGIN
				ROLLBACK
				RAISERROR('Não existe IP maior que 255255255255, OU menor que 1000',16,2)
			END
		END
		ELSE
		BEGIN
			ROLLBACK
			RAISERROR('Serão aceito apenas números',16,2)
		END
	END
END

CREATE TRIGGER t_vldhttp ON requisicao
AFTER INSERT, UPDATE 
AS
    DECLARE @vldhttp INT
    SET @vldhttp = (SELECT http FROM inserted)
    IF( LEN(@vldhttp) > 3)
    BEGIN
         ROLLBACK 
         RAISERROR('O protocolo HTTP que você busca não existe', 16,2)
    END
    ELSE
    BEGIN
         IF( @vldhttp <100 OR @vldhttp > 599)
         BEGIN
              ROLLBACK 
              RAISERROR('O protocolo HTTP que você digitou não existe favor informar um protocolo HTTP entre 100 e 599', 16,2)
         END
    END

CREATE TRIGGER t_checklen ON pagina
AFTER INSERT, UPDATE 
AS
	DECLARE @vldArq INT
	SET @vldArq = (SELECT lenArqBytes FROM inserted)
	IF (LEN(@vldArq) > 7 OR @vldArq >1048576)
	BEGIN
		ROLLBACK
		RAISERROR('São aceitos somentes arquivos com peso menor que 1MB por extenso',16,2)
	END

CREATE TRIGGER t_vldtarget ON link
AFTER INSERT, UPDATE
AS
	DECLARE @vldtarget VARCHAR (8)
	SET @vldtarget = (SELECT target FROM inserted)
	SET @vldtarget = (LOWER(@vldtarget))
	IF (@vldtarget != '_blank')
	BEGIN
		IF( @vldtarget != '_self')
		BEGIN
			IF (@vldtarget != '_parent')
			BEGIN
				IF( @vldtarget != '_top')
				BEGIN
					ROLLBACK
					RAISERROR('Os target que você inseriu não é valido são permitidos apenas: _blank, _self, _parent, _top',16,2)
				END
			END
		END
	END

CREATE TRIGGER t_vldtime ON	requisicao
AFTER INSERT, UPDATE
AS
	DECLARE @vldtime INT
	SET @vldtime = (SELECT tempo FROM inserted)
	IF(@vldtime > 60000)
	BEGIN
		ROLLBACK
		RAISERROR('timeout',16,3)
	END

CREATE FUNCTION fn_qtdLinks(@codigoLink VARCHAR(255))
RETURNS INT
AS
BEGIN
	DECLARE @qtdLink INT
		SET @qtdLink = (SELECT COUNT(li.URLdestino) FROM link li, paginalink pl WHERE  pl.linkURLdestino = li.URLdestino AND li.URLdestino = @codigoLink)
	RETURN @qtdLink
END


