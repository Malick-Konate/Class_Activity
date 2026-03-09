-- Drop tables if they exist to ensure a clean start
DROP TABLE IF EXISTS customer_phone_number;
DROP TABLE IF EXISTS service_request;
DROP TABLE IF EXISTS customer;

-- 1. Create Customer Table
CREATE TABLE customer
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    customer_id       VARCHAR(255) UNIQUE NOT NULL, -- Business Key (CustomerIdentifier)
    first_name        VARCHAR(255),
    last_name         VARCHAR(255),
    email             VARCHAR(255),
    registration_date TIMESTAMP,
    -- Embedded CustomerAddress
    street            VARCHAR(255),
    city              VARCHAR(255),
    province          VARCHAR(255),
    postal_code       VARCHAR(10),
    country           VARCHAR(100)
);

-- 2. Create Customer Phone Number Table (ElementCollection)
CREATE TABLE customer_phone_number
(
    customer_id VARCHAR(255),
    number      VARCHAR(50),
    type        VARCHAR(20),
    CONSTRAINT fk_customer_phone FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
);

-- 3. Create Service Request Table
CREATE TABLE service_request
(
    id                        INT AUTO_INCREMENT PRIMARY KEY,
    service_id                VARCHAR(255) UNIQUE NOT NULL, -- Business Key (ServiceRequestIdentifier)
    customer_id               VARCHAR(255),                 -- Reference to Customer business key
    urgency                   VARCHAR(50),                  -- UrgencyOfRequest Enum
    created_at                DATE,
    preferred_date            DATE,
    status                    VARCHAR(50),                  -- RequestStatus Enum

    -- Embedded ProblemDescription
    title                     VARCHAR(255),
    category                  VARCHAR(255),
    estimated_complexity      VARCHAR(50),
    details                   VARCHAR(255),

    -- Embedded SearchCriteria (including DateRange)
    service_category          VARCHAR(255),
    max_distance              INT,
    start_date                DATE,
    end_date                  DATE,
    min_rating                DECIMAL(3, 2),

    -- Embedded Preferences (including Money)
    amount                    DECIMAL(19, 2),
    currency                  VARCHAR(10),
    preferred_handyman_gender VARCHAR(20),
    language_preference       VARCHAR(50),
    require_insurance         BOOLEAN,

    CONSTRAINT fk_service_customer FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
);


-- Drop in safe order (children -> parents)
-- Drop in CORRECT safe order (children -> parents)
-- Drop tables in reverse order of dependency
-- Drop tables in reverse order of dependency
-- Drop tables if they exist to allow for clean re-runs
DROP TABLE IF EXISTS job_material_requirements;
DROP TABLE IF EXISTS jobs;
DROP TABLE IF EXISTS calendar_entries;
DROP TABLE IF EXISTS availability_calendars;
DROP TABLE IF EXISTS work_zones;
DROP TABLE IF EXISTS handyman_skills;
DROP TABLE IF EXISTS handyman_profiles;-- Main Handyman Profile Table
CREATE TABLE handyman_profiles
(
    id                SERIAL PRIMARY KEY,
    -- HandymanIdentifier (Embedded)
    handyman_id       VARCHAR(255) UNIQUE,
    first_name        VARCHAR(100),
    last_name         VARCHAR(100),
    email             VARCHAR(255),
    is_active         BOOLEAN,
    is_verified       BOOLEAN,
    registration_date TIMESTAMP,
    -- BackgroundCheck (Embedded)
    status            enum ('PENDING', 'IN_PROGRESS', 'PASSED', 'FAILED', 'EXPIRED'),
    performed_date    TIMESTAMP,
    expiry_date       TIMESTAMP,
    -- AvailableIdentifier (Embedded)
    calendar_id       VARCHAR(255)
);

-- Collection table for SkillSet
CREATE TABLE handyman_skills
(
    handyman_id       VARCHAR(255) NOT NULL,
    skill_name        VARCHAR(100),
    proficiency_level VARCHAR(50),
    CONSTRAINT fk_handyman_skills FOREIGN KEY (handyman_id)
        REFERENCES handyman_profiles (handyman_id) ON DELETE CASCADE
);

-- Collection table for WorkZone
CREATE TABLE work_zones
(
    handyman_id         VARCHAR(255) NOT NULL,
    city                VARCHAR(100),
    province            VARCHAR(10),
    max_travel_distance INTEGER,
    CONSTRAINT fk_work_zones FOREIGN KEY (handyman_id)
        REFERENCES handyman_profiles (handyman_id) ON DELETE CASCADE
);

-- Availability Calendar Table
CREATE TABLE availability_calendars
(
    id           SERIAL PRIMARY KEY,
    calendar_id  VARCHAR(255) UNIQUE NOT NULL,
    handyman_id  VARCHAR(255),
    last_updated TIMESTAMP,
    CONSTRAINT fk_calendar_handyman FOREIGN KEY (handyman_id)
        REFERENCES handyman_profiles (handyman_id) ON DELETE CASCADE
);

-- Collection table for CalendarEntry
CREATE TABLE calendar_entries
(
    calendar_id  VARCHAR(255) NOT NULL,
    day_of_week  VARCHAR(20)  NOT NULL,
    start_time   TIME,
    end_time     TIME,
    is_available BOOLEAN,
    CONSTRAINT fk_calendar_entries FOREIGN KEY (calendar_id)
        REFERENCES availability_calendars (calendar_id) ON DELETE CASCADE
);


-- 6. Jobs Table
CREATE TABLE jobs
(
    id                       SERIAL PRIMARY KEY,
    job_id                   VARCHAR(255) UNIQUE NOT NULL, -- From JobIdentifier
    status                   VARCHAR(50)         NOT NULL, -- Enum JobStatus
    created_at               TIMESTAMP           NOT NULL,
    job_scheduled_start_time TIMESTAMP,
    actual_start_time        TIMESTAMP,
    actual_end_time          TIMESTAMP,
    job_estimated_duration   INTEGER,
    notes                    VARCHAR(2000),
    -- Cross-context identifiers (Embedded)
    customer_id              VARCHAR(255),
    handyman_id              VARCHAR(255),
    service_id               VARCHAR(255),
    -- ServiceCategory (Embedded)
    category_name            VARCHAR(255),
    subcategory              VARCHAR(255),
    category_description     VARCHAR(1000),
    -- Schedule (Embedded)
    scheduled_date           DATE,
    slot_start_time          TIME,
    slot_end_time            TIME,
    schedule_duration        INTEGER,
    buffer_time_minutes      INTEGER,
    -- Assignment (Embedded)
    assigned_at              TIMESTAMP,
    assigned_by              VARCHAR(255),
    match_score              DECIMAL(5, 2),
    distance_km              DECIMAL(6, 2),
    -- JobLocation (Embedded)
    street                   VARCHAR(255),
    city                     VARCHAR(255),
    province                 VARCHAR(100),
    postal_code              VARCHAR(20),
    access_instructions      VARCHAR(1000)
);

-- 7. Job Material Requirements (Collection Table)
CREATE TABLE job_material_requirements
(
    job_id      VARCHAR(255)   NOT NULL,
    item_name   VARCHAR(255),
    quantity    INTEGER,
    amount      DECIMAL(19, 4) NOT NULL,
    currency    VARCHAR(3)     NOT NULL,
    provided_by VARCHAR(255),
    CONSTRAINT fk_job_materials FOREIGN KEY (job_id)
        REFERENCES jobs (job_id) ON DELETE CASCADE
);



CREATE TABLE job_tasks
(
    id                     SERIAL PRIMARY KEY,

    job_task_id            VARCHAR(255) UNIQUE NOT NULL,

    job_id                 VARCHAR(255)        NOT NULL,

    task_description       VARCHAR(500)        NOT NULL,

    status                 VARCHAR(50)         NOT NULL,

    estimated_time_minutes INTEGER,

    actual_time_minutes    INTEGER,

    requires_special_tools BOOLEAN,

    CONSTRAINT fk_job_tasks_job
        FOREIGN KEY (job_id)
            REFERENCES jobs (job_id)
            ON DELETE CASCADE
);


-- Drop tables if they exist to ensure a clean start
DROP TABLE IF EXISTS invoice_line_items;
DROP TABLE IF EXISTS invoices;

-- 1. Create Invoices Table (Aggregate Root)
CREATE TABLE invoices
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    invoice_number   VARCHAR(255) UNIQUE NOT NULL, -- From InvoiceIdentifier
    job_id           VARCHAR(255)        NOT NULL, -- From JobIdentifier
    issue_date       TIMESTAMP,
    due_date         TIMESTAMP,
    invoice_status   VARCHAR(50),                  -- Enum: DRAFT, ISSUED, PAID, etc.
    notes            VARCHAR(1000),

    -- Embedded TaxCalculation
    tax_rate         DECIMAL(19, 4),
    tax_type         VARCHAR(100),
    jurisdiction     VARCHAR(100),
    tax_amount       DECIMAL(19, 4),

    -- Embedded InvoiceTotal
    subtotal         DECIMAL(19, 4),
    tax_total        DECIMAL(19, 4),
    discount_total   DECIMAL(19, 4),
    grand_total      DECIMAL(19, 4),

    -- Embedded PlatformFee
    fee_percentage   DECIMAL(19, 4),
    fee_type         VARCHAR(100),
    fee_amount       DECIMAL(19, 4)
);

-- 2. Create Invoice Line Items Table (ElementCollection)
CREATE TABLE invoice_line_items
(
    invoice_number VARCHAR(255)   NOT NULL,
    description    VARCHAR(255)   NOT NULL,
    quantity       INTEGER        NOT NULL,
    unit_price     DECIMAL(19, 4) NOT NULL,
    subtotal       DECIMAL(19, 4) NOT NULL,
    CONSTRAINT fk_invoice_items FOREIGN KEY (invoice_number) REFERENCES invoices (invoice_number) ON DELETE CASCADE
);