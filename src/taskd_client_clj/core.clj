(ns taskd-client-clj.core)

(def client-info 
  (str
   "taskd-client-clj "
   (System/getProperty "taskd-client-clj.version")))

(def protocol-version "v1")

(def crlf \u000d)

(defn- build-message
  [type payload {:keys [organization user password]}]
  (let [message (str "type: " type crlf
                     "org: " organization crlf
                     "user: " user crlf
                     "key: " password crlf
                     "client: " client-info crlf
                     "protocol: " protocol-version crlf
                     crlf
                     payload)]
    (str ;(+ 4 (alength (.getBytes message))) 
         message)))

(defn sync-message
  [tasks sync-key auth]
  (build-message "sync"
                 (reduce #(str %1 %2 crlf) sync-key tasks)
                 auth))

(defn statistics-message
  [auth]
  (build-message "statistics"
                 nil
                 auth))

(def test-auth {:organization "Home"
                :user "test"
                :password "test-pw"})
  
(comment
  client-info
  crlf
  (build-message "sync" {} test-auth)
  (sync-message [{:task 1} {:task 2}] "123456" test-auth)
  (statistics-message test-auth))