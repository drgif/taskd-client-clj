(ns taskd-client-clj.core)

(def client-info 
  (str
   "taskd-client-clj "
   (System/getProperty "taskd-client-clj.version")))

(def protocol-version "v1")

(def crlf \u000d)

(defn build-message
  [type {:keys [organization user password sync-key payload]}]
  (str "<<size>>"
       "type: " type crlf
       "org: " organization crlf
       "user: " user crlf
       "key: " password crlf
       "client: " client-info crlf
       "protocol: " protocol-version crlf
       crlf))

(comment
  client-info
  crlf
  (build-message "sync" {:organization "Home"
                         :user "test"
                         :password "test-pw"}))