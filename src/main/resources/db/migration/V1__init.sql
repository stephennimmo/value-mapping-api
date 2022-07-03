CREATE TABLE system
(
    system_id SERIAL PRIMARY KEY,
    name      TEXT NOT NULL
);
ALTER SEQUENCE system_system_id_seq RESTART 1000000;

CREATE TABLE value_mapping
(
    value_mapping_id SERIAL PRIMARY KEY,
    source_system_id INT NOT NULL,
    source_value TEXT NOT NULL,
    target_system_id INT NOT NULL,
    target_value TEXT NOT NULL,
    CONSTRAINT fk_source_system_id FOREIGN KEY (source_system_id) REFERENCES system (system_id),
    CONSTRAINT fk_target_system_id FOREIGN KEY (target_system_id) REFERENCES system (system_id)
);
ALTER SEQUENCE value_mapping_value_mapping_id_seq RESTART 1000000;