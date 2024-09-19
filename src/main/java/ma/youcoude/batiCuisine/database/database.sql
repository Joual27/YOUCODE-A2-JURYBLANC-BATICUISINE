CREATE TABLE customers(
                          fullName VARCHAR(255) PRIMARY KEY,
                          adress TEXT,
                          phoneNumber VARCHAR(10),
                          isProfessional boolean
);


CREATE TYPE project_status AS ENUM('ONGOING','ENDED','CANCELLED');


CREATE TABLE projects(
                         projectId VARCHAR(255) PRIMARY KEY ,
                         projectName VARCHAR(255),
                         overallPrice double precision,
                         profitMargin double precision,
                         projectStatus project_status,
                         fullName VARCHAR(255),
                         FOREIGN KEY(fullName) REFERENCES customers (fullName)
);


CREATE TABLE estimates(
                          estimateId VARCHAR(255) PRIMARY KEY ,
                          estimatedOverallPrice double precision,
                          issuedAt Timestamps,
                          validityDate Date,
                          isAccepted boolean,
                          projectId VARCHAR(255),
                          FOREIGN KEY(projectId) REFERENCES projects (projectId)
);

CREATE TABLE components (
                            componentId VARCHAR(255) PRIMARY KEY,
                            componentName VARCHAR(255),
                            VAT double precision ,
                            projectId VARCHAR(255),
                            FOREIGN KEY(projectId) REFERENCES projects (projectId)
);

CREATE TABLE materials(
                          pricePerUnit double precision,
                          quantity int,
                          transportationCost double precision ,
                          qualityCoefficient double precision
) INHERITS (components);


CREATE TABLE workforce (
                           hourlyRate double precision,
                           workHours double precision ,
                           workerProductivityCoefficient double precision
) INHERITS (components);