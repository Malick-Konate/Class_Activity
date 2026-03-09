-- Insert 10 Customers
INSERT INTO customer (customer_id, first_name, last_name, email, registration_date, street, city, province, postal_code,
                      country)
VALUES ('CUSTOMER_100', 'Alice', 'Smith', 'alice@example.com', CURRENT_TIMESTAMP, '10 Main St', 'Montreal', 'QC',
        'H1A 1A1',
        'Canada'),
       ('CUSTOMER_101', 'Bob', 'Jones', 'bob@example.com', CURRENT_TIMESTAMP, '20 Oak Rd', 'Toronto', 'ON', 'M5V 2N2',
        'Canada'),
       ('CUSTOMER_102', 'Charlie', 'Brown', 'charlie@example.com', CURRENT_TIMESTAMP, '30 Pine Ave', 'Vancouver', 'BC',
        'V6B 1A1', 'Canada'),
       ('CUSTOMER_103', 'Diana', 'Prince', 'diana@example.com', CURRENT_TIMESTAMP, '40 Elm Dr', 'Calgary', 'AB',
        'T2P 1J1',
        'Canada'),
       ('CUSTOMER_104', 'Edward', 'Norton', 'edward@example.com', CURRENT_TIMESTAMP, '50 Birch Ln', 'Ottawa', 'ON',
        'K1P 5J2', 'Canada'),
       ('CUSTOMER_105', 'Fiona', 'Gallagher', 'fiona@example.com', CURRENT_TIMESTAMP, '60 Cedar St', 'Halifax', 'NS',
        'B3H 1A1', 'Canada'),
       ('CUSTOMER_106', 'George', 'Clooney', 'george@example.com', CURRENT_TIMESTAMP, '70 Willow Ct', 'Winnipeg', 'MB',
        'R3C 1A1', 'Canada'),
       ('CUSTOMER_107', 'Hannah', 'Abbott', 'hannah@example.com', CURRENT_TIMESTAMP, '80 Aspen Way', 'Quebec City',
        'QC',
        'G1R 1A1', 'Canada'),
       ('CUSTOMER_108', 'Ian', 'McKellen', 'ian@example.com', CURRENT_TIMESTAMP, '90 Spruce Blvd', 'Edmonton', 'AB',
        'T5J 1A1', 'Canada'),
       ('CUSTOMER_109', 'Julia', 'Roberts', 'julia@example.com', CURRENT_TIMESTAMP, '100 Hemlock Pl', 'Victoria', 'BC',
        'V8W 1A1', 'Canada');

-- Insert Phone Numbers
INSERT INTO customer_phone_number (customer_id, number, type)
VALUES ('CUSTOMER_100', '514-555-0101', 'MOBILE'),
       ('CUSTOMER_100', '416-555-0201', 'HOME'),
       ('CUSTOMER_102', '604-555-0301', 'WORK'),
       ('CUSTOMER_103', '416-555-0201', 'HOME'),
       ('CUSTOMER_104', '123-456-7890', 'WORK'),
       ('CUSTOMER_105', '444-555-6666', 'HOME'),
       ('CUSTOMER_108', '999-000-7331', 'WORK');
;

-- Insert 10 Service Requests
INSERT INTO service_request (service_id, customer_id, urgency, created_at, preferred_date, status,
                             title, category, estimated_complexity, details,
                             service_category, max_distance, start_date, end_date, min_rating,
                             amount, currency, preferred_handyman_gender, language_preference, require_insurance)
VALUES
-- 1. Emergency Plumbing
('sr-001', 'CUSTOMER_101', 'EMERGENCY', '2024-02-01', '2024-02-02', 'SUBMITTED',
 'Broken Pipe', 'Plumbing', 'HIGH', 'Water leaking in the basement from main line.',
 'Plumbing', 15, '2024-02-02', '2024-02-03', 4.5,
 250.00, 'CAD', 'NONE', 'English', 1),

-- 2. Electrical Repair
('sr-002', 'CUSTOMER_100', 'URGENT', '2024-02-05', '2024-02-07', 'IN_PROGRESS',
 'Flickering Lights', 'Electrical', 'MEDIUM', 'Kitchen lights flickering when microwave is on.',
 'Electrical', 10, '2024-02-07', '2024-02-10', 4.0,
 120.00, 'CAD', 'MALE', 'French', 1),

-- 3. Garden Maintenance
('sr-003', 'CUSTOMER_102', 'LOW', '2024-02-10', '2024-02-15', 'DRAFT',
 'Lawn Mowing', 'Landscaping', 'LOW', 'Bi-weekly lawn mowing and hedge trimming.',
 'Gardening', 20, '2024-02-15', '2024-02-28', 3.5,
 60.00, 'CAD', 'NONE', 'English', 0),

-- 4. Furniture Assembly
('sr-004', 'CUSTOMER_103', 'MEDIUM', '2024-02-12', '2024-02-13', 'SUBMITTED',
 'IKEA Desk Assembly', 'General', 'LOW', 'Need help assembling a large corner desk.',
 'Handyman', 5, '2024-02-13', '2024-02-14', 4.2,
 85.00, 'CAD', 'NONE', 'English', 0),

-- 5. Wall Painting
('sr-005', 'CUSTOMER_104', 'MEDIUM', '2024-02-14', '2024-02-20', 'COMPLETED',
 'Bedroom Painting', 'Renovation', 'MEDIUM', 'Painting two walls in the master bedroom with eggshell finish.',
 'Painting', 25, '2024-02-20', '2024-02-22', 4.8,
 300.00, 'CAD', 'FEMALE', 'French', 1),

-- 6. HVAC Filter Change
('sr-006', 'CUSTOMER_105', 'LOW', '2024-02-18', '2024-02-19', 'CANCELLED',
 'AC Filter Replacement', 'Maintenance', 'LOW', 'Seasonal filter change for central AC unit.',
 'HVAC', 15, '2024-02-19', '2024-02-20', 3.0,
 45.00, 'CAD', 'NONE', 'English', 0),

-- 7. Roof Shingle Repair
('sr-007', 'CUSTOMER_106', 'URGENT', '2024-02-20', '2024-02-21', 'SUBMITTED',
 'Missing Shingles', 'Exterior', 'HIGH', 'Strong winds blew off shingles; potential for leaks.',
 'Roofing', 30, '2024-02-21', '2024-02-25', 4.5,
 500.00, 'CAD', 'MALE', 'English', 1),

-- 8. Smart Doorbell Install
('sr-008', 'CUSTOMER_107', 'MEDIUM', '2024-02-22', '2024-02-23', 'IN_PROGRESS',
 'Ring Doorbell Setup', 'Tech', 'LOW', 'Installing and connecting a smart doorbell to WiFi.',
 'Electrical', 10, '2024-02-23', '2024-02-24', 4.0,
 75.00, 'CAD', 'NONE', 'English', 0),

-- 9. Gutter Cleaning
('sr-009', 'CUSTOMER_108', 'LOW', '2024-02-24', '2024-02-26', 'SUBMITTED',
 'Spring Cleaning', 'Exterior', 'MEDIUM', 'Removing leaves and debris from gutters on a 2-story house.',
 'Maintenance', 20, '2024-02-26', '2024-02-28', 4.2,
 150.00, 'CAD', 'NONE', 'French', 1),

-- 10. Drywall Patching
('sr-010', 'CUSTOMER_109', 'MEDIUM', '2024-02-25', '2024-02-27', 'SUBMITTED',
 'Hole in Wall', 'Repair', 'LOW', 'Patching a 5-inch hole in the living room drywall.',
 'Handyman', 10, '2024-02-27', '2024-02-28', 4.0,
 90.00, 'CAD', 'NONE', 'English', 0);


-- 1. Insert Availability Calendars
-- 1. Insert Availability Calendars
-- Insert Handyman Profiles
INSERT INTO handyman_profiles
(handyman_id, first_name, last_name, email, is_active, is_verified, registration_date, status, performed_date,
 expiry_date, calendar_id)
VALUES ('HMAN-001', 'Jean', 'Dupont', 'j.dupont@example.com', true, true, CURRENT_TIMESTAMP, 'PASSED',
        '2025-01-10 10:00:00', '2026-01-10 10:00:00', 'CAL-999'),
       ('HMAN-002', 'Sarah', 'Smith', 's.smith@example.com', false, false, CURRENT_TIMESTAMP, 'PENDING',
        CURRENT_TIMESTAMP, '2027-02-26 10:00:00', 'CAL-888');

-- Insert Skills (HMAN-001 has verified skills: INTERMEDIATE+)
INSERT INTO handyman_skills (handyman_id, skill_name, proficiency_level)
VALUES ('HMAN-001', 'Plumbing', 'EXPERT'),
       ('HMAN-001', 'Electrical', 'INTERMEDIATE'),
       ('HMAN-002', 'Painting', 'BEGINNER');

-- Insert Work Zones
INSERT INTO work_zones (handyman_id, city, province, max_travel_distance)
VALUES ('HMAN-001', 'Montreal', 'QC', 50),
       ('HMAN-001', 'Laval', 'QC', 30),
       ('HMAN-002', 'Toronto', 'ON', 25);

-- Insert Availability Calendar
INSERT INTO availability_calendars (calendar_id, handyman_id, last_updated)
VALUES ('CAL-999', 'HMAN-001', CURRENT_TIMESTAMP),
       ('CAL-888', 'HMAN-002', CURRENT_TIMESTAMP);

-- Insert Calendar Entries (e.g., Monday and Wednesday availability)
INSERT INTO calendar_entries (calendar_id, day_of_week, start_time, end_time, is_available)
VALUES ('CAL-999', 'MONDAY', '09:00:00', '17:00:00', true),
       ('CAL-999', 'WEDNESDAY', '13:00:00', '20:00:00', true),
       ('CAL-888', 'TUESDAY', '08:00:00', '16:00:00', true),
       ('CAL-888', 'FRIDAY', '14:00:00', '21:00:00', true);


INSERT INTO jobs (job_id,
                  status,
                  created_at,
                  job_scheduled_start_time,
                  actual_start_time,
                  actual_end_time,
                  job_estimated_duration,
                  notes,
                  customer_id,
                  handyman_id,
                  service_id,
                  category_name,
                  subcategory,
                  category_description,
                  scheduled_date,
                  slot_start_time,
                  slot_end_time,
                  schedule_duration,
                  buffer_time_minutes,
                  assigned_at,
                  assigned_by,
                  match_score,
                  distance_km,
                  street,
                  city,
                  province,
                  postal_code,
                  access_instructions)
VALUES

-- 1 COMPLETED Plumbing Emergency
('JOB-001', 'COMPLETED', '2024-02-01 09:00:00',
 '2024-02-02 10:00:00', '2024-02-02 10:05:00', '2024-02-02 12:00:00',
 120, 'Basement pipe replaced successfully.',
 'CUSTOMER_101', 'HMAN-001', 'sr-001',
 'Plumbing', 'Pipe Repair', 'Emergency plumbing repairs',
 '2024-02-02', '10:00:00', '12:00:00',
 120, 15,
 '2024-02-01 12:00:00', 'SYSTEM', 92.50, 8.40,
 '20 Oak Rd', 'Toronto', 'ON', 'M5V 2N2',
 'Basement access through side door'),

-- 2 IN_PROGRESS Electrical
('JOB-002', 'IN_PROGRESS', '2024-02-05 14:00:00',
 '2024-02-07 09:00:00', '2024-02-07 09:10:00', NULL,
 90, 'Investigating wiring load issue.',
 'CUSTOMER_100', 'HMAN-001', 'sr-002',
 'Electrical', 'Lighting Repair', 'Residential electrical troubleshooting',
 '2024-02-07', '09:00:00', '10:30:00',
 90, 10,
 '2024-02-06 11:00:00', 'SYSTEM', 88.30, 4.10,
 '10 Main St', 'Montreal', 'QC', 'H1A 1A1',
 'Breaker panel in garage'),

-- 3 CREATED Landscaping (Not yet assigned)
('JOB-003', 'CREATED', '2024-02-10 08:00:00',
 '2024-02-15 08:00:00', NULL, NULL,
 60, 'Scheduled bi-weekly mowing.',
 'CUSTOMER_102', 'HMAN-002', 'sr-003',
 'Gardening', 'Lawn Care', 'Outdoor landscaping services',
 '2024-02-15', '08:00:00', '09:00:00',
 60, 5,
 NULL, NULL, NULL, NULL,
 '30 Pine Ave', 'Vancouver', 'BC', 'V6B 1A1',
 'Backyard gate unlocked'),

-- 4 COMPLETED Furniture Assembly
('JOB-004', 'COMPLETED', '2024-02-12 09:30:00',
 '2024-02-13 13:00:00', '2024-02-13 13:05:00', '2024-02-13 15:30:00',
 150, 'Desk assembled and secured.',
 'CUSTOMER_103', 'HMAN-002', 'sr-004',
 'Handyman', 'Furniture Assembly', 'General handyman services',
 '2024-02-13', '13:00:00', '15:30:00',
 150, 10,
 '2024-02-12 10:00:00', 'ADMIN', 79.20, 3.00,
 '40 Elm Dr', 'Calgary', 'AB', 'T2P 1J1',
 'Call upon arrival'),

-- 5 COMPLETED Painting
('JOB-005', 'COMPLETED', '2024-02-14 10:00:00',
 '2024-02-20 09:00:00', '2024-02-20 09:00:00', '2024-02-20 14:00:00',
 300, 'Walls painted eggshell finish.',
 'CUSTOMER_104', 'HMAN-002', 'sr-005',
 'Painting', 'Interior Walls', 'Home renovation painting',
 '2024-02-20', '09:00:00', '14:00:00',
 300, 20,
 '2024-02-15 08:00:00', 'SYSTEM', 84.75, 6.20,
 '50 Birch Ln', 'Ottawa', 'ON', 'K1P 5J2',
 'Use protective sheets'),

-- 6 CANCELLED HVAC
('JOB-006', 'CANCELLED', '2024-02-18 09:00:00',
 '2024-02-19 10:00:00', NULL, NULL,
 45, 'Customer cancelled before visit.',
 'CUSTOMER_105', 'HMAN-001', 'sr-006',
 'HVAC', 'Filter Change', 'Maintenance services',
 '2024-02-19', '10:00:00', '11:00:00',
 45, 5,
 '2024-02-18 10:00:00', 'SYSTEM', 70.00, 9.00,
 '60 Cedar St', 'Halifax', 'NS', 'B3H 1A1',
 'Front entrance'),

-- 7 ASSIGNED Roofing
('JOB-007', 'SCHEDULED', '2024-02-20 08:30:00',
 '2024-02-21 11:00:00', NULL, NULL,
 240, 'Inspecting and replacing shingles.',
 'CUSTOMER_106', 'HMAN-001', 'sr-007',
 'Roofing', 'Shingle Repair', 'Exterior roofing services',
 '2024-02-21', '11:00:00', '15:00:00',
 240, 30,
 '2024-02-20 09:00:00', 'SYSTEM', 90.10, 12.50,
 '70 Willow Ct', 'Winnipeg', 'MB', 'R3C 1A1',
 'Ladder access required'),

-- 8 IN_PROGRESS Smart Doorbell
('JOB-008', 'IN_PROGRESS', '2024-02-22 10:00:00',
 '2024-02-23 14:00:00', '2024-02-23 14:10:00', NULL,
 75, 'Configuring WiFi connection.',
 'CUSTOMER_107', 'HMAN-002', 'sr-008',
 'Electrical', 'Smart Installation', 'Smart home setup services',
 '2024-02-23', '14:00:00', '15:15:00',
 75, 10,
 '2024-02-22 11:00:00', 'ADMIN', 82.40, 4.70,
 '80 Aspen Way', 'Quebec City', 'QC', 'G1R 1A1',
 'Doorbell box near garage');

INSERT INTO job_material_requirements
    (job_id, item_name, quantity, amount, currency, provided_by)
VALUES

-- Plumbing materials
('JOB-001', 'Copper Pipe 1/2 inch', 3, 45.00, 'CAD', 'HANDYMAN'),
('JOB-001', 'Pipe Sealant', 1, 12.50, 'CAD', 'HANDYMAN'),

-- Electrical
('JOB-002', 'Wire Connector Pack', 1, 18.00, 'CAD', 'HANDYMAN'),

-- Painting
('JOB-005', 'Eggshell Paint (4L)', 2, 120.00, 'CAD', 'CUSTOMER'),
('JOB-005', 'Paint Rollers', 2, 25.00, 'CAD', 'HANDYMAN'),

-- Roofing
('JOB-007', 'Roof Shingles Bundle', 4, 320.00, 'CAD', 'HANDYMAN'),

-- Smart Install
('JOB-008', 'Mounting Bracket', 1, 15.00, 'CAD', 'HANDYMAN');



INSERT INTO job_tasks
(job_task_id, job_id, task_description, status, estimated_time_minutes, actual_time_minutes, requires_special_tools)
VALUES

-- JOB-001 Plumbing
('TASK-001', 'JOB-001', 'Inspect leaking pipe', 'COMPLETED', 30, 25, false),
('TASK-002', 'JOB-001', 'Replace damaged pipe section', 'COMPLETED', 90, 95, true),

-- JOB-002 Electrical
('TASK-003', 'JOB-002', 'Inspect electrical wiring', 'IN_PROGRESS', 40, NULL, false),
('TASK-004', 'JOB-002', 'Fix loose wire connections', 'PENDING', 50, NULL, true),

-- JOB-003 Landscaping
('TASK-005', 'JOB-003', 'Mow lawn area', 'PENDING', 30, NULL, false),
('TASK-006', 'JOB-003', 'Trim hedges', 'PENDING', 30, NULL, false),

-- JOB-004 Furniture Assembly
('TASK-007', 'JOB-004', 'Unpack furniture components', 'COMPLETED', 30, 20, false),
('TASK-008', 'JOB-004', 'Assemble desk frame and drawers', 'COMPLETED', 120, 130, true),

-- JOB-005 Painting
('TASK-009', 'JOB-005', 'Prepare walls and cover furniture', 'COMPLETED', 60, 50, false),
('TASK-010', 'JOB-005', 'Apply paint coats to walls', 'COMPLETED', 240, 250, false),

-- JOB-006 HVAC
('TASK-011', 'JOB-006', 'Inspect HVAC filter condition', 'CANCELLED', 15, NULL, false),
('TASK-012', 'JOB-006', 'Replace HVAC filter', 'CANCELLED', 30, NULL, false),

-- JOB-007 Roofing
('TASK-013', 'JOB-007', 'Inspect roof shingles', 'PENDING', 60, NULL, true),
('TASK-014', 'JOB-007', 'Replace missing shingles', 'PENDING', 180, NULL, true),

-- JOB-008 Smart Doorbell
('TASK-015', 'JOB-008', 'Mount smart doorbell hardware', 'IN_PROGRESS', 30, 20, false),
('TASK-016', 'JOB-008', 'Configure WiFi and mobile app', 'PENDING', 45, NULL, false);


-- Insert 5 Invoices
-- Invariants: grand_total = subtotal + tax_total - discount_total
INSERT INTO invoices (invoice_number, job_id, issue_date, due_date, invoice_status, notes,
                      tax_rate, tax_type, jurisdiction, tax_amount,
                      subtotal, tax_total, discount_total, grand_total,
                      fee_percentage, fee_type, fee_amount)
VALUES ('INV-2026-001', 'JOB-001', '2026-03-01 10:00:00', '2026-03-15 17:00:00', 'PAID', 'Plumbing repair completed.',
        0.15, 'HST', 'Ontario', 18.00, 120.00, 18.00, 0.00, 138.00, 5.00, 'Service Fee', 6.00),

       ('INV-2026-002', 'JOB-002', '2026-03-02 11:30:00', '2026-03-16 17:00:00', 'ISSUED', 'Electrical inspection.',
        0.14975, 'TPS/TVQ', 'Quebec', 14.98, 100.00, 14.98, 10.00, 104.98, 5.00, 'Service Fee', 5.00),

       ('INV-2026-003', 'JOB-003', '2026-03-03 09:00:00', '2026-03-17 17:00:00', 'DRAFT', 'Lawn maintenance weekly.',
        0.05, 'GST', 'Alberta', 2.50, 50.00, 2.50, 0.00, 52.50, 2.00, 'Platform Fee', 1.00),

       ('INV-2026-004', 'JOB-004', '2026-03-04 14:00:00', '2026-03-18 17:00:00', 'OVERDUE',
        'Furniture assembly - 3 items.',
        0.13, 'HST', 'Ontario', 32.50, 250.00, 32.50, 20.00, 262.50, 5.00, 'Service Fee', 12.50),

       ('INV-2026-005', 'JOB-005', '2026-03-05 16:45:00', '2026-03-19 17:00:00', 'ISSUED',
        'Interior painting - living room.',
        0.15, 'HST', 'Nova Scotia', 75.00, 500.00, 75.00, 50.00, 525.00, 5.00, 'Service Fee', 25.00);

-- Insert Line Items for the Invoices
-- Invariant: subtotal = quantity * unit_price
INSERT INTO invoice_line_items (invoice_number, description, quantity, unit_price, subtotal)
VALUES ('INV-2026-001', 'Pipe Replacement', 1, 80.00, 80.00),
       ('INV-2026-001', 'Labor (1hr)', 1, 40.00, 40.00),

       ('INV-2026-002', 'Wiring Inspection', 2, 50.00, 100.00),

       ('INV-2026-003', 'Mowing and Edging', 1, 50.00, 50.00),

       ('INV-2026-004', 'Desk Assembly', 1, 100.00, 100.00),
       ('INV-2026-004', 'Chair Assembly', 2, 75.00, 150.00),

       ('INV-2026-005', 'Painting Service', 1, 400.00, 400.00),
       ('INV-2026-005', 'Premium Paint Gallon', 2, 50.00, 100.00);