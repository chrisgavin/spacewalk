--
-- Copyright (c) 2008--2012 Red Hat, Inc.
--
-- This software is licensed to you under the GNU General Public License,
-- version 2 (GPLv2). There is NO WARRANTY for this software, express or
-- implied, including the implied warranties of MERCHANTABILITY or FITNESS
-- FOR A PARTICULAR PURPOSE. You should have received a copy of GPLv2
-- along with this software; if not, see
-- http://www.gnu.org/licenses/old-licenses/gpl-2.0.txt.
--
-- Red Hat trademarks are not licensed under GPLv2. No permission is
-- granted to use or replicate Red Hat trademarks that are incorporated
-- in this software or its documentation.
--


CREATE TABLE rhnServerActionPackageResult
(
    server_id          NUMBER NOT NULL
                           CONSTRAINT rhn_sap_result_sid_fk
                               REFERENCES rhnServer (id),
    action_package_id  NUMBER NOT NULL
                           CONSTRAINT rhn_sap_result_apid_fk
                               REFERENCES rhnActionPackage (id)
                               ON DELETE CASCADE,
    result_code        NUMBER NOT NULL,
    stdout             BLOB,
    stderr             BLOB,
    created            timestamp with local time zone
                           DEFAULT (current_timestamp) NOT NULL,
    modified           timestamp with local time zone
                           DEFAULT (current_timestamp) NOT NULL
)
TABLESPACE [[blob]]
ENABLE ROW MOVEMENT
;

CREATE UNIQUE INDEX rhn_sap_result_sid_apid_uq
    ON rhnServerActionPackageResult (server_id, action_package_id)
    TABLESPACE [[4m_tbs]];

